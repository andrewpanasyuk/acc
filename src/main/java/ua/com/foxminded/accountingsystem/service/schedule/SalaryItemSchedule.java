package ua.com.foxminded.accountingsystem.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.SalaryItem;
import ua.com.foxminded.accountingsystem.repository.InvoiceRepository;
import ua.com.foxminded.accountingsystem.repository.SalaryItemRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalaryItemSchedule {

    private final InvoiceRepository invoiceRepository;
    private final SalaryItemRepository salaryItemRepository;

    @Autowired
    public SalaryItemSchedule(InvoiceRepository invoiceRepository, SalaryItemRepository salaryItemRepository) {
        this.invoiceRepository = invoiceRepository;
        this.salaryItemRepository = salaryItemRepository;
    }

    @Scheduled(cron = "${salary.item.creation.schedule}")
    public void scheduleSalaryItemCreation() {
        List<Invoice> invoiceList = invoiceRepository.findAllByCurrentDayAndNotAssignedToSalaryItemsAndLessThenCurrentDate(LocalDate.now().getDayOfMonth() - 1);
        for (Invoice invoice : invoiceList) {
            createSalaryItem(invoice);
        }
    }

    private void createSalaryItem(Invoice invoice) {
        Money employeePayment = new Money(invoice.getContract().getEmployeeRate().getAmount(),
            invoice.getContract().getEmployeeRate().getCurrency());

        SalaryItem salaryItem = new SalaryItem(invoice.getContract().getEmployee(), invoice, employeePayment,
            invoice.getPaymentPeriodFrom(), invoice.getPaymentPeriodTo());

        salaryItemRepository.save(salaryItem);
    }
}
