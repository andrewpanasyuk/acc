package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractServiceJPA implements ContractService {

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
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract prepareNewByOrderId(Long orderId){

        Order order = orderService.findOne(orderId);
        order.setStatus(OrderStatus.ACTIVE);

        Contract contract = new Contract();

        Money employeeRate = new Money();
        employeeRate.setPrice(order.getService().getEmployeeRate().getPrice());
        employeeRate.setCurrency(Currency.UAH);

        Money price = new Money();
        for (Money curPrice: order.getService().getPrices()) {
            if (curPrice.getCurrency() == Currency.UAH){
                price.setPrice(curPrice.getPrice());
            }
        }
        price.setCurrency(Currency.UAH);

        contract.setOrder(order);
        contract.setContractDate(LocalDate.now());
        contract.setEmployeeRate(employeeRate);
        contract.setPrice(price);

        return contract;

    }

    @Override
    public List<Invoice> prepareInvoicesForPayment(LocalDate today) {
        int signalPeriod = 3;
        List<Invoice> invoices = new ArrayList<>();
        LocalDate payDay = today.plusDays(signalPeriod);
        List<Contract> contracts = contractRepository.findContractsForPayment(payDay.getDayOfMonth(),
            today);
        for(Contract contract: contracts){
            Invoice invoice = new Invoice();
            invoice.setContract(contract);
            invoice.setCreationDate(today);
            invoice.setPrice(contract.getPrice());
            invoice.setEmployeePaid(false);
            if(contract.getPaymentType() == PaymentType.PREPAY || contract.getPaymentType() == PaymentType.TRIAL){
                invoice.setPaymentPeriodFrom(today.plusDays(signalPeriod));
                invoice.setPaymentPeriodTo(today.plusDays(signalPeriod).plusMonths(1));
            }
            else{
                invoice.setPaymentPeriodFrom(today.plusDays(signalPeriod).minusMonths(1));
                invoice.setPaymentPeriodTo(today.plusDays(signalPeriod));
            }
            invoices.add(invoice);
        }
        return invoices;
    }
}


