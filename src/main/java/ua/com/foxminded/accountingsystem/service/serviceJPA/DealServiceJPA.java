package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.CloseType;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.repository.ClientRepository;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;
import ua.com.foxminded.accountingsystem.repository.DealRepository;
import ua.com.foxminded.accountingsystem.service.DealQueueService;
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
    public void makeNew(Deal deal) {

        if (deal == null) {
            throw new ChangingDealStatusException("Argument in activate method is null !");
        }

        Deal refreshedDeal = dealRepository.findOne(deal.getId());
        DealStatus oldStatus = refreshedDeal.getStatus();
        DealStatus newStatus = DealStatus.NEW;

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                " to " + newStatus + " is not allowed !");
        }

        refreshedDeal.setStatus(newStatus);
        save(refreshedDeal);
    }

    @Override
    @Transactional
    public void makeActive(Deal deal) {

        if (deal == null) {
            throw new ChangingDealStatusException("Argument in activate method is null !");
        }

        Deal refreshedDeal = dealRepository.findOne(deal.getId());
        DealStatus oldStatus = refreshedDeal.getStatus();
        DealStatus newStatus = DealStatus.ACTIVE;

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                " to " + newStatus + " is not allowed !");
        }

        refreshedDeal.setStatus(newStatus);
        save(refreshedDeal);
    }

    @Override
    @Transactional
    public void makeWaiting(Deal deal) {

        if (deal == null) {
            throw new ChangingDealStatusException("Argument in wait method is null !");
        }

        Deal refreshedDeal = dealRepository.findOne(deal.getId());
        DealStatus oldStatus = refreshedDeal.getStatus();
        DealStatus newStatus = DealStatus.WAITING;

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                " to " + newStatus + " is not allowed !");
        }

        refreshedDeal.setStatus(newStatus);
        save(refreshedDeal);
    }

    @Override
    @Transactional
    public void makeFrozen(Deal deal) {

        if (deal == null) {
            throw new ChangingDealStatusException("Argument in freeze method is null !");
        }

        Deal refreshedDeal = dealRepository.findOne(deal.getId());
        DealStatus oldStatus = refreshedDeal.getStatus();
        DealStatus newStatus = DealStatus.FROZEN;

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                " to " + newStatus + " is not allowed !");
        }

        if (oldStatus == DealStatus.ACTIVE) {
            Invoice lastInvoice = invoiceService.findLastInvoiceInActiveContractByDealId(refreshedDeal.getId());
            if (lastInvoice != null) {
                salaryItemService.createPretermSalaryItem(lastInvoice, LocalDate.now());
            }
            changeRelatedContractStatus(refreshedDeal, newStatus);
        }

        refreshedDeal.setStatus(newStatus);
        save(refreshedDeal);
    }

    @Override
    @Transactional
    public void makeRefused(Deal deal) {

        if (deal == null) {
            throw new ChangingDealStatusException("Argument in refuse method is null !");
        }

        Deal refreshedDeal = dealRepository.findOne(deal.getId());
        DealStatus oldStatus = refreshedDeal.getStatus();
        DealStatus newStatus = DealStatus.REFUSED;

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                " to " + newStatus + " is not allowed !");
        }

        if (oldStatus == DealStatus.ACTIVE) {
            Invoice lastInvoice = invoiceService.findLastInvoiceInActiveContractByDealId(refreshedDeal.getId());
            if (lastInvoice != null) {
                salaryItemService.createPretermSalaryItem(lastInvoice, LocalDate.now());
            }
            changeRelatedContractStatus(refreshedDeal, newStatus);
        }

        refreshedDeal.setCloseDate(LocalDate.now());
        refreshedDeal.setStatus(newStatus);
        save(refreshedDeal);
    }

    @Override
    @Transactional
    public void makeRejected(Deal deal) {

        if (deal == null) {
            throw new ChangingDealStatusException("Argument in reject method is null !");
        }

        Deal refreshedDeal = dealRepository.findOne(deal.getId());
        DealStatus oldStatus = refreshedDeal.getStatus();
        DealStatus newStatus = DealStatus.REJECTED;

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                " to " + newStatus + " is not allowed !");
        }

        if (oldStatus == DealStatus.ACTIVE) {
            Invoice lastInvoice = invoiceService.findLastInvoiceInActiveContractByDealId(refreshedDeal.getId());
            if (lastInvoice != null) {
                salaryItemService.createPretermSalaryItem(lastInvoice, LocalDate.now());
            }
            changeRelatedContractStatus(refreshedDeal, newStatus);
        }

        refreshedDeal.setCloseDate(LocalDate.now());
        refreshedDeal.setStatus(newStatus);
        save(refreshedDeal);
    }

    @Override
    @Transactional
    public void makeCompleted(Deal deal) {

        if (deal == null) {
            throw new ChangingDealStatusException("Argument in complete method is null !");
        }

        Deal refreshedDeal = dealRepository.findOne(deal.getId());
        DealStatus oldStatus = refreshedDeal.getStatus();
        DealStatus newStatus = DealStatus.COMPLETED;

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                " to " + newStatus + " is not allowed !");
        }

        Invoice lastInvoice = invoiceService.findLastInvoiceInActiveContractByDealId(refreshedDeal.getId());
        if (lastInvoice != null) {
            salaryItemService.createPretermSalaryItem(lastInvoice, LocalDate.now());
        }

        changeRelatedContractStatus(refreshedDeal, newStatus);
        refreshedDeal.setCloseDate(LocalDate.now());
        refreshedDeal.setStatus(newStatus);
        save(refreshedDeal);
    }

    @Override
    public Deal createDealByClientId(Long id) {
        Client client = clientRepository.findOne(id);
        Deal deal = new Deal();
        deal.setClient(client);
        makeNew(deal);
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

    private void changeRelatedContractStatus(Deal deal, DealStatus newStatus) {

        Contract contract = contractRepository.findContractByDealIdAndCloseTypeIsNull(deal.getId());

        if (contract == null) {
            throw new ChangingDealStatusException("Could not find related contract !");
        }

        if (newStatus == DealStatus.FROZEN) {
            contract.setCloseType(CloseType.FROZEN);
            contract.setClosingDescription("freeze");
        }

        if (newStatus == DealStatus.COMPLETED || newStatus == DealStatus.REFUSED || newStatus == DealStatus.REJECTED) {
            contract.setCloseType(CloseType.COMPLETED);
            contract.setClosingDescription("complete");
        }

        contract.setCloseDate(LocalDate.now());
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

        if (oldStatus == DealStatus.ACTIVE &&
            (newStatus == DealStatus.COMPLETED || newStatus == DealStatus.FROZEN ||
                newStatus == DealStatus.REFUSED || newStatus == DealStatus.REJECTED)) {
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
