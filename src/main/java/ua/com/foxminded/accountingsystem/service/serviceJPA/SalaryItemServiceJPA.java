package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.SalaryItem;
import ua.com.foxminded.accountingsystem.repository.SalaryItemRepository;
import ua.com.foxminded.accountingsystem.service.SalaryItemService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class SalaryItemServiceJPA implements SalaryItemService {

    private final SalaryItemRepository salaryItemRepository;

    @Autowired
    public SalaryItemServiceJPA(SalaryItemRepository salaryItemRepository) {
        this.salaryItemRepository = salaryItemRepository;
    }

    @Override
    public void delete(SalaryItem salaryItem) {
        salaryItemRepository.delete(salaryItem);
    }

    @Override
    public SalaryItem save(SalaryItem salaryItem) {
        return salaryItemRepository.save(salaryItem);
    }

    @Override
    public SalaryItem findOne(Long id) {
        return salaryItemRepository.findOne(id);
    }

    @Override
    public List<SalaryItem> findAll() {
        return salaryItemRepository.findAll();
    }

    @Override
    public SalaryItem createSalaryItem(Invoice invoice) {
        SalaryItem salaryItem = new SalaryItem();
        salaryItem.setInvoice(invoice);
        salaryItem.setEmployee(invoice.getContract().getEmployee());
        salaryItem.setDateFrom(invoice.getPaymentPeriodFrom());
        salaryItem.setDateTo(invoice.getPaymentPeriodTo());
        Money employeePayment = new Money();
        employeePayment.setCurrency(invoice.getContract().getEmployeeRate().getCurrency());
        employeePayment.setPrice(invoice.getContract().getEmployeeRate().getPrice());
        salaryItem.setEmployeePayment(employeePayment);

        return salaryItemRepository.save(salaryItem);
    }

    @Override
    public SalaryItem createPretermSalaryItem(Invoice invoice, LocalDate closureDate) {
        SalaryItem salaryItem = new SalaryItem();
        long daysInPeriod = ChronoUnit.DAYS.between(invoice.getPaymentPeriodFrom(), invoice.getPaymentPeriodTo());
        long salaryItemPeriod = ChronoUnit.DAYS.between(invoice.getPaymentPeriodFrom(), closureDate);
        long employeePaymentLongValue = invoice.getContract().getEmployeeRate().getPrice() / daysInPeriod * salaryItemPeriod;
        int employeePaymentValue = Long
            .valueOf(employeePaymentLongValue)
            .intValue();

        Money employeePayment = new Money();
        employeePayment.setCurrency(invoice.getContract().getEmployeeRate().getCurrency());
        employeePayment.setPrice(employeePaymentValue);

        salaryItem.setInvoice(invoice);
        salaryItem.setEmployee(invoice.getContract().getEmployee());
        salaryItem.setDateFrom(invoice.getPaymentPeriodFrom());
        salaryItem.setDateTo(closureDate);
        salaryItem.setEmployeePayment(employeePayment);

        return salaryItemRepository.save(salaryItem);
    }
}
