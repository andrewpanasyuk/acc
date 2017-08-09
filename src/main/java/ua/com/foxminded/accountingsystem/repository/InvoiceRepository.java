package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Invoice;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("SELECT distinct invoice FROM Invoice invoice LEFT JOIN invoice.payment payment WHERE payment.invoice IS NULL " +
        "AND ((invoice.contract.paymentType = ua.com.foxminded.accountingsystem.model.PaymentType.PREPAY " +
        "AND invoice.paymentPeriodFrom < current_date )" +
        "OR(invoice.contract.paymentType = ua.com.foxminded.accountingsystem.model.PaymentType.POSTPAY " +
        "AND invoice.paymentPeriodTo < current_date ))")
    List<Invoice> findDebtInvoices();

    Invoice findFirstByContractDealIdOrderByCreationDateDesc(long dealId);
}
