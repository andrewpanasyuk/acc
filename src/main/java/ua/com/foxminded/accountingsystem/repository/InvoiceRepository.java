package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("SELECT distinct invoice FROM Invoice invoice LEFT JOIN invoice.payment payment WHERE payment.invoice IS NULL " +
        "AND ((invoice.contract.paymentType = ua.com.foxminded.accountingsystem.model.PaymentType.PREPAY " +
        "AND invoice.paymentPeriodFrom < current_date )" +
        "OR(invoice.contract.paymentType = ua.com.foxminded.accountingsystem.model.PaymentType.POSTPAY " +
        "AND invoice.paymentPeriodTo < current_date ))")
    List<Invoice> findDebtInvoices();

    Invoice findFirstByContractDealIdOrderByCreationDateDesc(long dealId);

    @Query("SELECT invoice FROM Invoice invoice WHERE invoice.paymentPeriodTo < CURRENT_DATE " +
        "AND FUNCTION('DAY', invoice.paymentPeriodTo) = ?1 " +
        "AND NOT EXISTS (SELECT si.invoice FROM SalaryItem AS si WHERE si.invoice.id = invoice.id) ")
    List<Invoice> findAllByCurrentDayAndNotAssignedToSalaryItemsAndLessThenCurrentDate(int day);

    List<Invoice> findInvoicesByContract(Contract contract);

    @Query("SELECT invoice FROM Invoice invoice WHERE invoice.contract.id = ?1 AND ?2 BETWEEN " +
        "invoice.paymentPeriodFrom AND invoice.paymentPeriodTo")
    Invoice findInvoiceByDateWithinPaymentPeriod(long contractId, LocalDate date);
}
