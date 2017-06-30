package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;
import ua.com.foxminded.accountingsystem.repository.InvoiceRepository;
import ua.com.foxminded.accountingsystem.service.InvoiceService;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.MONTHS;

@Service
public class InvoiceServiceJPA implements InvoiceService {

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


    //Assembling Invoice object from available information in Contract
    public Invoice assemble(Long contractId) {
        //Initialization block
        Contract contract = contractRepository.findOne(contractId);
        Invoice invoice = new Invoice();
        Money amountForInvoice = contract.getPrice();

        //Date logic block
        LocalDate contractPaymentDate = contract.getPaymentDate();
        LocalDate currentDate = LocalDate.now();
        long monthsPassedSinceFirstPayment = MONTHS.between(contractPaymentDate, currentDate);
        LocalDate paymentDate = contractPaymentDate.plusMonths(monthsPassedSinceFirstPayment + 1L);

        //Setting block
        invoice.setPaymentPeriodTo(paymentDate);
        invoice.setPaymentPeriodFrom(paymentDate.minusMonths(1L));
        invoice.setPrice(amountForInvoice);
        invoice.setCreationDate(LocalDate.now());
        invoice.setContract(contract);
        invoice.setEmployeePaid(false);
        return invoice;
    }
}
