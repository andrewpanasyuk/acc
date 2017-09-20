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
import ua.com.foxminded.accountingsystem.service.DealService;
import ua.com.foxminded.accountingsystem.service.InvoiceService;
import ua.com.foxminded.accountingsystem.service.SalaryItemService;
import ua.com.foxminded.accountingsystem.service.exception.ChangingDealStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class DealServiceJPA implements DealService {

    private final DealStatus NO_STATUS = null;

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
    public void changeDealStatus(Deal deal, DealStatus newStatus) {

        if (deal == null || newStatus == null) {
            throw new ChangingDealStatusException("Incorrect argument !");
        }

        DealStatus oldStatus = null;
        Deal refreshedDeal = null;

        if (deal.getId() == null) {
            oldStatus = deal.getStatus();
        } else {
            refreshedDeal = dealRepository.findOne(deal.getId());
            if (refreshedDeal == null) {
                throw new ChangingDealStatusException("Could not find deal !");
            }

            oldStatus = refreshedDeal.getStatus();
        }

        if (!isChangingDealStatusAllowed(oldStatus, newStatus)) {
            throw new ChangingDealStatusException("Changing deal status from " + oldStatus +
                                                  " to " + newStatus + " is not allowed !");
        }

        if ((deal.getId() == null) && (oldStatus == NO_STATUS) && (newStatus == DealStatus.NEW)) {
            deal.setStatus(newStatus);
        }

        if ((oldStatus == DealStatus.NEW || oldStatus == DealStatus.WAITING || oldStatus == DealStatus.FROZEN)
            && (newStatus == DealStatus.ACTIVE || newStatus == DealStatus.WAITING)) {

            refreshedDeal.setStatus(newStatus);
            save(refreshedDeal);

            return;
        }

        if ((oldStatus == DealStatus.ACTIVE) &&
            (newStatus == DealStatus.COMPLETED || newStatus == DealStatus.FROZEN ||
                newStatus == DealStatus.REFUSED || newStatus == DealStatus.REJECTED)) {

            Invoice lastInvoice = invoiceService.findLastInvoiceInActiveContractByDealId(refreshedDeal.getId());
            if (lastInvoice != null) {
                salaryItemService.createPretermSalaryItem(lastInvoice, LocalDate.now());
            }

            if (newStatus != DealStatus.FROZEN) {
                refreshedDeal.setCloseDate(LocalDate.now());
            }

            changeRelatedContractStatus(refreshedDeal.getId(), newStatus);
            refreshedDeal.setStatus(newStatus);
            save(refreshedDeal);

            return;
        }

        if ((oldStatus == DealStatus.WAITING) &&
            (newStatus == DealStatus.NEW || newStatus == DealStatus.FROZEN ||
                newStatus == DealStatus.REFUSED || newStatus == DealStatus.REJECTED)) {

            if (newStatus != DealStatus.NEW && newStatus != DealStatus.FROZEN) {
                refreshedDeal.setCloseDate(LocalDate.now());
            }

            refreshedDeal.setStatus(newStatus);
            save(refreshedDeal);

            return;
        }

        if ((oldStatus == DealStatus.NEW) &&
            (newStatus == DealStatus.REFUSED || newStatus == DealStatus.REJECTED)) {

            refreshedDeal.setCloseDate(LocalDate.now());
            refreshedDeal.setStatus(newStatus);
            save(refreshedDeal);

            return;
        }
    }

    @Override
    public Deal createDealByClientId(Long id) {
        Client client = clientRepository.findOne(id);
        Deal deal = new Deal();
        deal.setClient(client);
        changeDealStatus(deal, DealStatus.NEW);
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

    private void changeRelatedContractStatus(Long id, DealStatus newStatus) {

        Contract contract = contractRepository.findContractByDealIdAndCloseTypeIsNull(id);

        if (contract == null) {
            throw new ChangingDealStatusException("Could not find related contract !");
        }

        if (newStatus == DealStatus.FROZEN) {
            contract.setCloseType(CloseType.FROZEN);
            contract.setClosingDescription("freeze");
        }

        if ((newStatus == DealStatus.COMPLETED) || (newStatus == DealStatus.REFUSED) || (newStatus == DealStatus.REJECTED)) {
            contract.setCloseType(CloseType.COMPLETED);
            contract.setClosingDescription("complete");
        }

        contract.setCloseDate(LocalDate.now());
        contractRepository.save(contract);
    }

    private boolean isChangingDealStatusAllowed(DealStatus oldStatus, DealStatus newStatus) {

        if ((oldStatus == NO_STATUS) && (newStatus == DealStatus.NEW)) {
            return true;
        }

        if ((oldStatus == DealStatus.NEW) &&
            (newStatus == DealStatus.ACTIVE || newStatus == DealStatus.WAITING ||
                newStatus == DealStatus.REFUSED || newStatus == DealStatus.REJECTED)) {
            return true;
        }

        if ((oldStatus == DealStatus.ACTIVE) &&
            (newStatus == DealStatus.COMPLETED || newStatus == DealStatus.FROZEN ||
                newStatus == DealStatus.REFUSED || newStatus == DealStatus.REJECTED)) {
            return true;
        }

        if ((oldStatus == DealStatus.WAITING) &&
            (newStatus == DealStatus.NEW || newStatus == DealStatus.ACTIVE || newStatus == DealStatus.FROZEN ||
                newStatus == DealStatus.REFUSED || newStatus == DealStatus.REJECTED)) {
            return true;
        }

        if ((oldStatus == DealStatus.FROZEN) &&
            (newStatus == DealStatus.ACTIVE || newStatus == DealStatus.WAITING)) {
            return true;
        }

        return false;
    }
}
