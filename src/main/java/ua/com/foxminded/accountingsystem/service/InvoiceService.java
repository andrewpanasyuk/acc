package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Payment;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {

    List<Invoice> findAll();

    Invoice findById(long id);

    Invoice save(Invoice invoice);

    void delete(Invoice invoice);

    List<Invoice> findDebtInvoices();

    Invoice createInvoiceByContractId(Long contractId);

    void addPayment(Payment payment);

    Invoice issueInvoice(Invoice invoice);

    Invoice findLastInvoiceInActiveContractByDealId(Long dealId);

    Invoice findInvoiceByDate(long contractId, LocalDate date);

    List<Invoice> findInvoicesByContract(Contract contract);
}
