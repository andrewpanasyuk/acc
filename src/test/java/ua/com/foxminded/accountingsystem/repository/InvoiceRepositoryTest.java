package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class InvoiceRepositoryTest extends AbstractRepositoryTest<InvoiceRepository> {

    private static Invoice invoice;
    private static Contract contract;

    @BeforeClass
    public static void init() {
        Money money = new Money();
        money.setId(1L);
        money.setCurrency(Currency.EUR);
        money.setAmount(1500);

        contract = new Contract();
        contract.setCreatedBy("system");
        contract.setCreatedDate(LocalDateTime.now());

        invoice = new Invoice();
        invoice.setId(1L);
        invoice.setContract(contract);
        invoice.setPrice(money);
        invoice.setCreationDate(LocalDate.of(2010, 1, 1));
        invoice.setPaymentPeriodFrom(LocalDate.of(2010, 1, 1));
        invoice.setPaymentPeriodTo(LocalDate.of(2010, 1, 1).plusMonths(1L));
        invoice.setEmployeePaid(true);
        invoice.setCreatedBy("system");
        invoice.setCreatedDate(LocalDateTime.now());
    }

    @Test
    @Commit
    @DataSet(value = "invoices/empty.xml", disableConstraints = true, cleanBefore = true)
    @ExpectedDataSet(value = "invoices/expected-invoices.xml", ignoreCols = {"id", "contract_id", "price_id", "created_by", "created_date"})
    public void invoiceIsAdded() {
        em.persist(contract);
        repository.save(invoice);
    }

    @Test
    @DataSet("invoices/stored-invoices.xml")
    public void ifNoInvoiceFoundReturnNull() {
        assertThat(repository.findOne(150L), nullValue());
    }

    @Test
    @DataSet(value = "invoices/empty.xml", disableConstraints = true, cleanBefore = true)
    public void ifNoInvoicesFoundReturnEmptyList() {
        assertThat(repository.findAll(), is(empty()));
    }

    @Test
    @Commit
    @DataSet("invoices/expected-invoices.xml")
    @ExpectedDataSet("invoices/empty.xml")
    public void invoiceIsDeleted() {
        repository.delete(invoice);
    }
}
