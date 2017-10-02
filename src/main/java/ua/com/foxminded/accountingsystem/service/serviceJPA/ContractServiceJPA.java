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
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Payment;
import ua.com.foxminded.accountingsystem.model.PaymentType;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;
import ua.com.foxminded.accountingsystem.repository.DealQueueRepository;
import ua.com.foxminded.accountingsystem.repository.PaymentRepository;
import ua.com.foxminded.accountingsystem.service.ContractService;
import ua.com.foxminded.accountingsystem.service.DealService;
import ua.com.foxminded.accountingsystem.service.exception.ActiveContractExistsException;
import ua.com.foxminded.accountingsystem.service.exception.ObjectCannotBeDeleted;
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


    @Autowired
    public ContractServiceJPA(ContractRepository contractRepository, DealService dealService, PaymentRepository paymentRepository, DealQueueRepository dealQueueRepository) {
        this.contractRepository = contractRepository;
        this.dealService = dealService;
        this.paymentRepository = paymentRepository;
        this.dealQueueRepository = dealQueueRepository;
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
        if (contract.getPaymentType() == PaymentType.TRIAL){
            if (checkIfDealHasHistory(contract)) {
                throw new ObjectCannotBeDeleted("Contract has already history and it cannot be deleted");
            }
        }

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

    private boolean checkIfDealHasHistory(Contract contract){
        if (findAllByDeal(contract.getDeal()).size() > 1){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Contract save(Contract contract) {

        if (existsActiveContractByDeal(contract.getDeal())) {
            throw new ActiveContractExistsException("Contract hasn't been created ! "
                + "Current deal has already had an active contract !");
        }

        if (contract.getId() == null && !contract.getContractDate().isAfter(LocalDate.now())) {
            dealService.changeStatus(contract.getDeal(), DealStatus.ACTIVE);
        }

        return contractRepository.save(contract);
    }

    @Override
    public Contract prepareNewByDealId(Long dealId) {

        Deal deal = dealService.findOne(dealId);

        Contract contract = new Contract();

        Money employeeRate = new Money();
        employeeRate.setAmount(deal.getConsultancy().getEmployeeRate().getAmount());
        employeeRate.setCurrency(Currency.UAH);

        Money price = new Money();
        for (Money curPrice : deal.getConsultancy().getPrices()) {
            if (curPrice.getCurrency() == Currency.UAH) {
                price.setAmount(curPrice.getAmount());
            }
        }
        price.setCurrency(Currency.UAH);

        contract.setPaymentType(PaymentType.PREPAY);
        contract.setDeal(deal);
        contract.setContractDate(LocalDate.now());
        contract.setPaymentDate(LocalDate.now());
        contract.setEmployeeRate(employeeRate);
        contract.setPrice(price);

        return contract;
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
    public Contract findOpenedContractByDealId(Long dealId) {
        return contractRepository.findContractByDealIdAndCloseTypeIsNull(dealId);
    }

    @Override
    public boolean existsContractByDeal(Deal deal) {
        return contractRepository.existsContractByDeal(deal);
    }

    @Override
    public boolean existsActiveContractByDeal(Deal deal) {
        return contractRepository.existsContractByDealAndCloseDateIsNullAndCloseTypeIsNull(deal);
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


