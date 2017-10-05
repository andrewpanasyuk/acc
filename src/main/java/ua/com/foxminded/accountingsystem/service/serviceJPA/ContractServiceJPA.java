package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.accountingsystem.model.CloseType;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealQueue;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.model.Payment;
import ua.com.foxminded.accountingsystem.model.PaymentType;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;
import ua.com.foxminded.accountingsystem.repository.DealQueueRepository;
import ua.com.foxminded.accountingsystem.repository.PaymentRepository;
import ua.com.foxminded.accountingsystem.service.ContractService;
import ua.com.foxminded.accountingsystem.service.DealService;
import ua.com.foxminded.accountingsystem.service.InvoiceService;
import ua.com.foxminded.accountingsystem.service.SalaryItemService;
import ua.com.foxminded.accountingsystem.service.exception.ActiveContractExistsException;
import ua.com.foxminded.accountingsystem.service.exception.ContractCreatingException;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractServiceJPA implements ContractService {

    @Value("${signalPeriod}")
    private int signalPeriod;

    private final ContractRepository contractRepository;
    private final DealService dealService;
    private final PaymentRepository paymentRepository;
    private final DealQueueRepository dealQueueRepository;
    private final InvoiceService invoiceService;
    private final SalaryItemService salaryItemService;


    @Autowired
    public ContractServiceJPA(ContractRepository contractRepository, DealService dealService, PaymentRepository paymentRepository,
                              DealQueueRepository dealQueueRepository, InvoiceService invoiceService, SalaryItemService salaryItemService) {
        this.contractRepository = contractRepository;
        this.dealService = dealService;
        this.paymentRepository = paymentRepository;
        this.dealQueueRepository = dealQueueRepository;
        this.invoiceService = invoiceService;
        this.salaryItemService = salaryItemService;
    }

    @Override
    public void close(Contract contract, CloseType closeType, String cause) {

        if (contract.getPaymentType() != PaymentType.TRIAL) {
            salaryItemService.createSalaryItemByDeal(contract.getDeal());
        }

        contract.setCloseType(closeType);
        contract.setClosingDescription(cause);
        contract.setCloseDate(LocalDate.now());
        contractRepository.save(contract);
    }

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract findOne(Long id) {
        return contractRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Contract contract = contractRepository.findOne(id);
        Deal deal = contract.getDeal();
        List<DealQueue> dealQueues = dealQueueRepository.findAllByDealAndQueuingDateOrderById(deal, contract.getContractDate());

        if (dealQueues.isEmpty()) {
            List<Contract> contracts = contractRepository.findAllByDealAndContractDateLessThanOrderByContractDateDesc(deal, contract.getContractDate());
            dealService.changeStatus(deal, chooseDealStatusByPreviousContracts(contracts));
        } else {
            DealQueue dealQueue = dealQueues.get(0);
            dealQueue.setRemoved(false);
            dealQueueRepository.save(dealQueue);

            dealService.changeStatus(deal, DealStatus.WAITING);
        }
        contractRepository.delete(id);
    }

    @Override
    @Transactional
    public Contract save(Contract contract) {

        if (existsActiveContractByDeal(contract.getDeal())) {

            Contract activeContract = findActiveContractByDeal(contract.getDeal());

            if (activeContract.getPaymentType() == PaymentType.TRIAL) {
                close(activeContract, CloseType.COMPLETED, "Trial contract was closed and paid contract was created");
            } else {
                throw new ActiveContractExistsException("Contract hasn't been created ! "
                    + "Current deal has already had an active contract !");
            }
        }

        if (isDealActivationRequired(contract)) {
            dealService.changeStatus(contract.getDeal(), DealStatus.ACTIVE);
        }

        return contractRepository.save(contract);
    }

    private boolean isDealActivationRequired(Contract contract) {

        return (contract.getId() == null
                && !contract.getContractDate().isAfter(LocalDate.now())
                && contract.getDeal().getStatus() != DealStatus.ACTIVE);
    }

    @Override
    public Contract prepareNewContractByDealId(Long dealId) {

        Deal deal = dealService.findOne(dealId);

        if (deal == null) {
            throw new ContractCreatingException("Deal is null !");
        }

        Contract trialContract = findTrialActiveContractByDeal(deal);

        if (trialContract == null) {
           return constructNewContractByDefault(deal);
        } else {
           return constructNewContractByTrialContract(deal, trialContract);
        }
    }

    private Contract constructNewContractByDefault(Deal deal) {

        return new Contract(LocalDate.now(),
                            deal,
                            new Money(deal.getConsultancy().getPriceByCurrency(Currency.UAH)),
                            PaymentType.PREPAY,
                            null,
                            new Money(deal.getConsultancy().getEmployeeRate()),
                            LocalDate.now());
    }

    private Contract constructNewContractByTrialContract(Deal deal, Contract trialContract) {

        return new Contract(LocalDate.now(),
                            deal,
                            new Money(trialContract.getPrice()),
                            PaymentType.PREPAY,
                            trialContract.getEmployee(),
                            new Money(trialContract.getEmployeeRate()),
                            LocalDate.now());
    }

    @Override
    public List<Invoice> prepareIssueInvoices() {
        LocalDate today = LocalDate.now();
        List<Invoice> invoices = new ArrayList<>();
        LocalDate payDay = today.plusDays(signalPeriod);
        LocalDate paymentPeriodFrom;
        LocalDate paymentPeriodTo;
        List<Contract> contracts = contractRepository.findContractsForInvoicesCreation(payDay.getDayOfMonth());
        for (Contract contract : contracts) {
            if (contract.getPaymentType() == PaymentType.PREPAY) {
                paymentPeriodFrom = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), contract.getPaymentDate().getDayOfMonth());
                paymentPeriodTo = paymentPeriodFrom.plusMonths(1).minusDays(1L);
            } else {
                paymentPeriodTo = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), contract.getPaymentDate().getDayOfMonth());
                paymentPeriodFrom = paymentPeriodTo.minusMonths(1);
            }
            invoices.add(new Invoice(today, contract, paymentPeriodFrom, paymentPeriodTo, contract.getPrice()));
        }
        return invoices;
    }

    @Override
    public List<Contract> findAllByDeal(Deal deal) {
        return contractRepository.findAllByDealOrderByContractDateDesc(deal);
    }

    @Override
    public Contract findActiveContractByDeal(Deal deal) {
        return contractRepository.findContractByDealAndCloseTypeIsNull(deal);
    }

    @Override
    public Contract findTrialActiveContractByDeal(Deal deal) {
        return contractRepository.findTrialActiveContractByDeal(deal);
    }

    @Override
    public boolean existsContractByDeal(Deal deal) {
        return contractRepository.existsContractByDeal(deal);
    }

    @Override
    public boolean existsActiveContractByDeal(Deal deal) {
        return contractRepository.existsContractByDealAndCloseTypeIsNull(deal);
    }

    @Override
    public List<Payment> findAllRelatedPayments(Contract contract) {
        return paymentRepository.findAllByInvoiceContractOrderByDatePaid(contract);
    }

    @Override
    public List<Contract> findAllByPaymentType(PaymentType paymentType) {
        return contractRepository.findAllByPaymentType(paymentType);
    }

    private DealStatus matchDealStatusWithContractCloseType(CloseType closeType) {

        if (closeType == CloseType.FROZEN) {
            return DealStatus.FROZEN;
        } else if (closeType == CloseType.COMPLETED) {
            return DealStatus.COMPLETED;
        } else {
            return DealStatus.ACTIVE;
        }
    }

    private DealStatus chooseDealStatusByPreviousContracts(List<Contract> contracts) {

        if (contracts.isEmpty()) {
            return DealStatus.NEW;
        } else {
            CloseType contractCloseType = contracts.get(0).getCloseType();
            if (contractCloseType == null) {
                return DealStatus.ACTIVE;
            } else {
                return matchDealStatusWithContractCloseType(contractCloseType);
            }
        }
    }
}


