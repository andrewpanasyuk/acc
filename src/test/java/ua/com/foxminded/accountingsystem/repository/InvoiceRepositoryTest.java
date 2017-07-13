package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class InvoiceRepositoryTest extends AbstractRepositoryTest<InvoiceRepository> {

    private static Invoice invoice_1;
    private static Contract contract_1;

    @Autowired
    private ContractRepository contractRepository;

    @BeforeClass
    public static void init() {
        Money money_1 = new Money();
        money_1.setId(1L);
        money_1.setCurrency(Currency.EUR);
        money_1.setPrice(1500);

        contract_1 = new Contract();
        contract_1.setId(50L);
        contract_1.setCreatedBy("system");
        contract_1.setCreatedDate(LocalDateTime.now());

        invoice_1 = new Invoice();
        invoice_1.setId(1L);
        invoice_1.setContract(contract_1);
        invoice_1.setPrice(money_1);
        invoice_1.setCreationDate(LocalDate.of(2010, 1, 1));
        invoice_1.setPaymentPeriodFrom(LocalDate.of(2010, 1, 1));
        invoice_1.setPaymentPeriodTo(LocalDate.of(2010, 1, 1).plusMonths(1L));
        invoice_1.setEmployeePaid(true);
        invoice_1.setCreatedBy("system");
        invoice_1.setCreatedDate(LocalDateTime.now());
    }

    @Test
    @Commit
    @DataSet(value = "invoices/empty.xml", disableConstraints = true, cleanBefore = true)
    @ExpectedDataSet(value = "invoices/expected-invoices.xml", ignoreCols = {"id", "contract_id", "closing_description", "close_date", "close_type", "contract_date", "order_id", "price_id", "payment_date", "payment_type", "open_date", "employee_id", "employee_rate_id", "close_date", "created_by", "created_date"})
    public void invoiceIsAdded() {
        contractRepository.save(contract_1);
        repository.save(invoice_1);
    }

    @Test
    @DataSet("invoices/stored-invoices.xml")
    public void invoiceExistsInRepo() {
        assertThat(repository.findAll(), hasItems(invoice_1));
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
        repository.delete(invoice_1);
    }
}
