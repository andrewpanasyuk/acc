package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderStatus;
import ua.com.foxminded.accountingsystem.model.PaymentType;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;
import ua.com.foxminded.accountingsystem.service.ContractService;
import ua.com.foxminded.accountingsystem.service.OrderService;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractServiceJPA implements ContractService {

    @Value("${signalPeriod}")
    private int signalPeriod;

    private final ContractRepository contractRepository;
    private final OrderService orderService;


    @Autowired
    public ContractServiceJPA(ContractRepository contractRepository, OrderService orderService) {
        this.contractRepository = contractRepository;
        this.orderService = orderService;
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
            Order order = contract.getOrder();
            order.setStatus(OrderStatus.ACTIVE);
            orderService.save(order);
        }
        return contractRepository.save(contract);
    }

    @Override
    public Contract prepareNewByOrderId(Long orderId) {

        Order order = orderService.findOne(orderId);
        order.setStatus(OrderStatus.ACTIVE);

        Contract contract = new Contract();

        Money employeeRate = new Money();
        employeeRate.setAmount(order.getService().getEmployeeRate().getAmount());
        employeeRate.setCurrency(Currency.UAH);

        Money price = new Money();
        for (Money curPrice : order.getService().getPrices()) {
            if (curPrice.getCurrency() == Currency.UAH) {
                price.setAmount(curPrice.getAmount());
            }
        }
        price.setCurrency(Currency.UAH);

        contract.setPaymentType(PaymentType.PREPAY);
        contract.setOrder(order);
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
            if (contract.getPaymentType() == PaymentType.PREPAY || contract.getPaymentType() == PaymentType.TRIAL) {
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
    public List<Contract> findAllByOrder(Order order) {
        return contractRepository.findAllByOrderOrderByContractDateDesc(order);
    }

    @Override
    public Contract findOpenedContractByOrderId(Long orderId) {
        return contractRepository.findContractByOrderIdAndCloseTypeIsNull(orderId);
    }
}


