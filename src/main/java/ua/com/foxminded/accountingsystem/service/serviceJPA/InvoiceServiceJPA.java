package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Payment;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;
import ua.com.foxminded.accountingsystem.repository.InvoiceRepository;
import ua.com.foxminded.accountingsystem.service.InvoiceService;
import ua.com.foxminded.accountingsystem.service.exception.InvoiceException;

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

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice findById(long id) {
        return invoiceRepository.findOne(id);
    }

    @Override
    public Invoice save(Invoice invoice) {
        boolean existsInvoiceFrom = invoiceRepository.existsInvoiceByContractAndPaymentPeriodFromLessThanEqualAndPaymentPeriodToGreaterThanEqual(invoice.getContract(), invoice.getPaymentPeriodFrom(), invoice.getPaymentPeriodFrom());
        boolean existsInvoiceTo = invoiceRepository.existsInvoiceByContractAndPaymentPeriodFromLessThanEqualAndPaymentPeriodToGreaterThanEqual(invoice.getContract(), invoice.getPaymentPeriodTo(), invoice.getPaymentPeriodTo());
        if (existsInvoiceFrom || existsInvoiceTo) {
            throw new InvoiceException("You have invoice for selected period");
        }
        return invoiceRepository.save(invoice);
    }

    @Override
    public void delete(Invoice invoice) {
        invoiceRepository.delete(invoice);
    }

    @Override
    public Invoice createInvoiceByContractId(Long contractId) {
        Contract contract = contractRepository.findOne(contractId);
        Invoice invoice = new Invoice();
        Money money = new Money();
        money.setAmount(contract.getPrice().getAmount());
        money.setCurrency(contract.getPrice().getCurrency());

        LocalDate contractPaymentDate = contract.getPaymentDate();
        LocalDate currentDate = LocalDate.now();
        long monthsPassedSinceFirstPayment = MONTHS.between(contractPaymentDate, currentDate);
        LocalDate paymentDate = contractPaymentDate.plusMonths(monthsPassedSinceFirstPayment + 1L);

        invoice.setPaymentPeriodTo(paymentDate.minusDays(1L));
        invoice.setPaymentPeriodFrom(paymentDate.minusMonths(1L));
        invoice.setPrice(money);
        invoice.setCreationDate(LocalDate.now());
        invoice.setContract(contract);
        return invoice;
    }

    @Override
    public List<Invoice> findDebtInvoices() {
        return invoiceRepository.findDebtInvoices();
    }

    @Override
    public void addPayment(Payment payment) {
        Invoice invoice = invoiceRepository.findOne(payment.getInvoice().getId());
        invoice.addPayment(payment);
        invoiceRepository.save(invoice);
    }

    @Override
    public Invoice issueInvoice(Invoice invoice) {
        Contract contract = contractRepository.findOne(invoice.getContract().getId());
        contract.addInvoice(invoice);
        Invoice saved = invoiceRepository.save(invoice);
        log.info("New invoice created: {}", invoice);
        return saved;
    }

    @Override
    public Invoice findLastInvoiceInActiveContractByDeal(Deal deal) {
        return invoiceRepository.findFirstByContractDealOrderByCreationDateDesc(deal);
    }

    @Override
    public List<Invoice> findInvoicesByContract(Contract contract) {
        return invoiceRepository.findInvoicesByContract(contract);
    }
}
