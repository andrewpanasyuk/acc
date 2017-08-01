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

    private static long calculateEmployeePayment(Invoice invoice) {
        long daysInPeriod = ChronoUnit.DAYS.between(invoice.getPaymentPeriodFrom(), invoice.getPaymentPeriodTo());
        long salaryItemPeriod = ChronoUnit.DAYS.between(invoice.getPaymentPeriodFrom(), LocalDate.now());
        return invoice.getContract().getEmployeeRate().getPrice() / daysInPeriod * salaryItemPeriod;
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
    public void createSalaryItem(Invoice invoice) {
        Money employeePayment = new Money(invoice.getContract().getEmployeeRate().getPrice(), invoice.getContract().getEmployeeRate().getCurrency());
        SalaryItem salaryItem = new SalaryItem(invoice.getContract().getEmployee(), invoice, employeePayment, invoice.getPaymentPeriodFrom(), invoice.getPaymentPeriodTo());
        salaryItemRepository.save(salaryItem);
    }

    @Override
    public void createPretermSalaryItem(Invoice invoice) {
        //TODO: Remove int cast when Money.amount will be long
        Money employeePayment = new Money((int) calculateEmployeePayment(invoice), invoice.getContract().getEmployeeRate().getCurrency());
        SalaryItem salaryItem = new SalaryItem(invoice.getContract().getEmployee(), invoice, employeePayment, invoice.getPaymentPeriodFrom(), LocalDate.now());
        salaryItemRepository.save(salaryItem);
    }
}
