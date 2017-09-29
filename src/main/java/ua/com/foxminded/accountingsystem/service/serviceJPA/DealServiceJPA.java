package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.CloseType;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.PaymentType;
import ua.com.foxminded.accountingsystem.repository.ClientRepository;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;
import ua.com.foxminded.accountingsystem.repository.DealRepository;
import ua.com.foxminded.accountingsystem.service.DealService;
import ua.com.foxminded.accountingsystem.service.InvoiceService;
import ua.com.foxminded.accountingsystem.service.SalaryItemService;
import ua.com.foxminded.accountingsystem.service.exception.ChangingDealStatusException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class DealServiceJPA implements DealService {

    private final DealRepository dealRepository;
    private final ClientRepository clientRepository;
    private final ContractRepository contractRepository;
    private final InvoiceService invoiceService;
    private final SalaryItemService salaryItemService;

    @Autowired
    public DealServiceJPA(DealRepository dealRepository, ClientRepository clientRepository,
                          ContractRepository contractRepository, InvoiceService invoiceService,
                          SalaryItemService salaryItemService) {

        this.dealRepository = dealRepository;
        this.clientRepository = clientRepository;
        this.contractRepository = contractRepository;
        this.invoiceService = invoiceService;
        this.salaryItemService = salaryItemService;
    }

    @Override
    @Transactional
    public void changeStatus(Deal deal, DealStatus newStatus) {

        if (deal == null || newStatus == null) {
            throw new ChangingDealStatusException("Argument in changeStatus method is null !");
        }

        if (newStatus == DealStatus.NEW) {
            setNew(deal);
        }
        if (newStatus == DealStatus.ACTIVE) {
            setActive(deal);
        }
        if (newStatus == DealStatus.WAITING) {
            setWaiting(deal);
        }
        if (newStatus == DealStatus.FROZEN) {
            setFrozen(deal);
        }
        if (newStatus == DealStatus.REFUSED) {
            setRefused(deal);
        }
        if (newStatus == DealStatus.REJECTED) {
            setRejected(deal);
        }
        if (newStatus == DealStatus.COMPLETED) {
            setCompleted(deal);
        }
    }

    @Override
    @Transactional
    public void setNew(Deal deal) {

        DealStatus oldStatus = deal.getStatus();
        DealStatus newStatus = DealStatus.NEW;

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                " to " + newStatus + " is not allowed !");
        }

        deal.setStatus(newStatus);

        if (deal.getId() != null && oldStatus != null) {
            save(deal);
        }
    }

    @Override
    @Transactional
    public void setActive(Deal deal) {

        DealStatus oldStatus = deal.getStatus();
        DealStatus newStatus = DealStatus.ACTIVE;

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                " to " + newStatus + " is not allowed !");
        }

        deal.setStatus(newStatus);
        save(deal);
    }

    @Override
    @Transactional
    public void setWaiting(Deal deal) {

        DealStatus oldStatus = deal.getStatus();
        DealStatus newStatus = DealStatus.WAITING;

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                " to " + newStatus + " is not allowed !");
        }

        deal.setStatus(newStatus);
        save(deal);
    }

    @Override
    @Transactional
    public void setFrozen(Deal deal) {

        DealStatus oldStatus = deal.getStatus();
        DealStatus newStatus = DealStatus.FROZEN;

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                " to " + newStatus + " is not allowed !");
        }

        if (oldStatus == DealStatus.ACTIVE) {
            Invoice lastInvoice = invoiceService.findLastInvoiceInActiveContractByDeal(deal);
            if (lastInvoice != null) {
                salaryItemService.createPretermSalaryItem(lastInvoice, LocalDate.now());
            }
            changeRelatedContractStatus(deal, LocalDate.now(), newStatus);
        }

        deal.setStatus(newStatus);
        save(deal);
    }

    @Override
    @Transactional
    public void setFrozen(Deal deal, LocalDate closeDate) {

        if (deal == null) {
            throw new ChangingDealStatusException("Deal is null !");
        }

        DealStatus oldStatus = deal.getStatus();
        DealStatus newStatus = DealStatus.FROZEN;

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                " to " + newStatus + " is not allowed !");
        }

        if (oldStatus == DealStatus.ACTIVE) {
            Invoice lastInvoice = invoiceService.findLastInvoiceInActiveContractByDeal(deal);
            if (lastInvoice != null) {
                salaryItemService.createPretermSalaryItem(lastInvoice, closeDate);
            }
            changeRelatedContractStatus(deal, closeDate, newStatus);
        }

        deal.setStatus(newStatus);
        save(deal);
    }

    @Override
    @Transactional
    public void setRefused(Deal deal) {

        DealStatus oldStatus = deal.getStatus();
        DealStatus newStatus = DealStatus.REFUSED;

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                " to " + newStatus + " is not allowed !");
        }

        if (oldStatus == DealStatus.ACTIVE) {
            Invoice lastInvoice = invoiceService.findLastInvoiceInActiveContractByDeal(deal);
            if (lastInvoice != null) {
                salaryItemService.createPretermSalaryItem(lastInvoice, LocalDate.now());
            }
            changeRelatedContractStatus(deal, LocalDate.now(), newStatus);
        }

        deal.setCloseDate(LocalDate.now());
        deal.setStatus(newStatus);
        save(deal);
    }

    @Override
    @Transactional
    public void setRejected(Deal deal) {

        DealStatus oldStatus = deal.getStatus();
        DealStatus newStatus = DealStatus.REJECTED;

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                " to " + newStatus + " is not allowed !");
        }

        if (oldStatus == DealStatus.ACTIVE) {
            Invoice lastInvoice = invoiceService.findLastInvoiceInActiveContractByDeal(deal);
            if (lastInvoice != null) {
                salaryItemService.createPretermSalaryItem(lastInvoice, LocalDate.now());
            }
            changeRelatedContractStatus(deal, LocalDate.now(), newStatus);
        }

        deal.setCloseDate(LocalDate.now());
        deal.setStatus(newStatus);
        save(deal);
    }

    @Override
    @Transactional
    public void setCompleted(Deal deal) {

        DealStatus oldStatus = deal.getStatus();
        DealStatus newStatus = DealStatus.COMPLETED;

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                " to " + newStatus + " is not allowed !");
        }

        Invoice lastInvoice = invoiceService.findLastInvoiceInActiveContractByDeal(deal);
        if (lastInvoice != null) {
            salaryItemService.createPretermSalaryItem(lastInvoice, LocalDate.now());
        }

        changeRelatedContractStatus(deal, LocalDate.now(), newStatus);
        deal.setCloseDate(LocalDate.now());
        deal.setStatus(newStatus);
        save(deal);
    }

    @Override
    public Deal createDealByClientId(Long id) {
        Client client = clientRepository.findOne(id);
        Deal deal = new Deal();
        deal.setClient(client);
        changeStatus(deal, DealStatus.NEW);
        client.getDeals().add(deal);
        deal.setOpenDate(LocalDate.now());
        return deal;
    }

    @Override
    public void delete(Deal deal) {
        dealRepository.delete(deal);
    }

    @Override
    public Deal save(Deal deal) {
        return dealRepository.save(deal);
    }

    @Override
    public Deal findOne(Long id) {
        return dealRepository.findOne(id);
    }

    @Override
    public List<Deal> findAll() {
        return dealRepository.findAll();
    }

    @Override
    public List<Deal> findDealsByStatus(DealStatus dealStatus) {
        return dealRepository.findDealsByStatus(dealStatus);
    }

    @Override
    public PaymentType findDealCurrentPaymentType(Deal deal) {

        Contract contract = contractRepository.findContractByDealAndCloseTypeIsNull(deal);

        return (contract == null ? null : contract.getPaymentType());
    }

    private void changeRelatedContractStatus(Deal deal, LocalDate closeDate, DealStatus newStatus) {

        Contract contract = contractRepository.findContractByDealAndCloseTypeIsNull(deal);

        if (contract == null) {
            throw new ChangingDealStatusException("Could not find related contract !");
        }

        if (newStatus == DealStatus.FROZEN) {
            contract.setCloseType(CloseType.FROZEN);
            contract.setClosingDescription("freeze");
            contract.setCloseDate(closeDate);
        }

        if (newStatus == DealStatus.COMPLETED || newStatus == DealStatus.REFUSED || newStatus == DealStatus.REJECTED) {
            contract.setCloseType(CloseType.COMPLETED);
            contract.setClosingDescription("complete");
            contract.setCloseDate(closeDate);
        }

        contractRepository.save(contract);
    }

    private boolean isChangingDealStatusAllowed(DealStatus oldStatus, DealStatus newStatus) {

        if (oldStatus == null && newStatus == DealStatus.NEW) {
            return true;
        }

        if (oldStatus == DealStatus.NEW &&
            (newStatus == DealStatus.ACTIVE || newStatus == DealStatus.WAITING ||
                newStatus == DealStatus.REFUSED || newStatus == DealStatus.REJECTED)) {
            return true;
        }

        if (oldStatus == DealStatus.ACTIVE) {
            return true;
        }

        if (oldStatus == DealStatus.WAITING &&
            (newStatus == DealStatus.NEW || newStatus == DealStatus.ACTIVE || newStatus == DealStatus.FROZEN ||
                newStatus == DealStatus.REFUSED || newStatus == DealStatus.REJECTED)) {
            return true;
        }

        if (oldStatus == DealStatus.FROZEN &&
            (newStatus == DealStatus.ACTIVE || newStatus == DealStatus.WAITING)) {
            return true;
        }

        return false;
    }
}
