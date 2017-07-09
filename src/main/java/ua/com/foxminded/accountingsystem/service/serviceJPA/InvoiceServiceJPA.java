package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Payment;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;
import ua.com.foxminded.accountingsystem.repository.InvoiceRepository;
import ua.com.foxminded.accountingsystem.service.InvoiceService;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.MONTHS;

@Service
public class InvoiceServiceJPA implements InvoiceService {

    private static final Logger log = LoggerFactory.getLogger(InvoiceServiceJPA.class);

    private final InvoiceRepository invoiceRepository;
    private final ContractRepository contractRepository;

    @Autowired
    public InvoiceServiceJPA(InvoiceRepository invoiceRepository, ContractRepository contractRepository) {
        this.invoiceRepository = invoiceRepository;
        this.contractRepository = contractRepository;
    }

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    public Invoice findById(long id) {
        return invoiceRepository.findOne(id);
    }

    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public void delete(Invoice invoice) {
        invoiceRepository.delete(invoice);
    }

    public Invoice createInvoiceByContractId(Long contractId) {
        Contract contract = contractRepository.findOne(contractId);
        Invoice invoice = new Invoice();
        Money amountForInvoice = contract.getPrice();

        LocalDate contractPaymentDate = contract.getPaymentDate();
        LocalDate currentDate = LocalDate.now();
        long monthsPassedSinceFirstPayment = MONTHS.between(contractPaymentDate, currentDate);
        LocalDate paymentDate = contractPaymentDate.plusMonths(monthsPassedSinceFirstPayment + 1L);

        invoice.setPaymentPeriodTo(paymentDate);
        invoice.setPaymentPeriodFrom(paymentDate.minusMonths(1L));
        invoice.setPrice(amountForInvoice);
        invoice.setCreationDate(LocalDate.now());
        invoice.setContract(contract);
        return invoice;
    }

    @Override
    public void addPayment(Payment payment) {
        log.warn(payment.toString());
        Invoice invoice = invoiceRepository.findOne(payment.getInvoice().getId());
        invoice.addPayment(payment);
        invoiceRepository.save(invoice);
    }
}
