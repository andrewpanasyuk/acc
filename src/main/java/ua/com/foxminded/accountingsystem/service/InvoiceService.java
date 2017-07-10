package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Payment;

import java.util.List;

public interface InvoiceService {

    List<Invoice> findAll();

    Invoice findById(long id);

    Invoice save(Invoice invoice);

    void delete(Invoice invoice);

    Invoice createInvoiceByContractId(Long contractId);

    void addPayment(Payment payment);

    Invoice issueInvoice(Invoice invoice);
}
