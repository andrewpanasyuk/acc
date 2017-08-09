package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.model.PaymentType;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;
import ua.com.foxminded.accountingsystem.service.ContractService;
import ua.com.foxminded.accountingsystem.service.DealService;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractServiceJPA implements ContractService {

    @Value("${signalPeriod}")
    private int signalPeriod;

    private final ContractRepository contractRepository;
    private final DealService dealService;


    @Autowired
    public ContractServiceJPA(ContractRepository contractRepository, DealService dealService) {
        this.contractRepository = contractRepository;
        this.dealService = dealService;
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
    public void delete(Long id) {
        contractRepository.delete(id);
    }

    @Override
    @Transactional
    public Contract save(Contract contract) {
        if (contract.getId() == null && !contract.getContractDate().isAfter(LocalDate.now())){
            Deal deal = contract.getDeal();
            deal.setStatus(DealStatus.ACTIVE);
            dealService.save(deal);
        }
        return contractRepository.save(contract);
    }

    @Override
    public Contract prepareNewByDealId(Long dealId) {

        Deal deal = dealService.findOne(dealId);
        deal.setStatus(DealStatus.ACTIVE);

        Contract contract = new Contract();

        Money employeeRate = new Money();
        employeeRate.setAmount(deal.getService().getEmployeeRate().getAmount());
        employeeRate.setCurrency(Currency.UAH);

        Money price = new Money();
        for (Money curPrice : deal.getService().getPrices()) {
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
        List<Contract> contracts = contractRepository.findContractsForInvoicesCreation(payDay.getDayOfMonth(),
            today);
        for (Contract contract : contracts) {
            if (contract.getPaymentType() == PaymentType.PREPAY) {
                paymentPeriodFrom = today.plusDays(signalPeriod);
                paymentPeriodTo = today.plusDays(signalPeriod).plusMonths(1);
            } else {
                paymentPeriodFrom = today.plusDays(signalPeriod).minusMonths(1);
                paymentPeriodTo = today.plusDays(signalPeriod);
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
}


