package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class InvoiceRepositoryTest extends AbstractRepositoryTest<InvoiceRepository> {

    private static Invoice invoice_1;
//    private static Invoice invoice_2;

    @BeforeClass
    public static void init() {
        Money money_1 = new Money();
        money_1.setId(50L);
        money_1.setCurrency(Currency.EUR);
        money_1.setPrice(1500);
        Money moneyOfContractTwo = new Money();
        moneyOfContractTwo.setId(2L);
        moneyOfContractTwo.setCurrency(Currency.EUR);
        moneyOfContractTwo.setPrice(2000);

        Contract contract_1 = new Contract();
        contract_1.setId(50L);
        contract_1.setPaymentDate(LocalDate.of(2010, 1, 1));

        invoice_1 = new Invoice();
        invoice_1.setId(50L);
        invoice_1.setContract(contract_1);
        invoice_1.setPrice(money_1);
        invoice_1.setCreationDate(contract_1.getPaymentDate());
        invoice_1.setPaymentPeriodFrom(contract_1.getPaymentDate());
        invoice_1.setPaymentPeriodTo(contract_1.getPaymentDate().plusMonths(1L));
        invoice_1.setEmployeePaid(true);

    }


    @Test
    @Commit
    @DataSet(value = "invoices/empty.xml", disableConstraints = true, cleanBefore = true)
    @ExpectedDataSet(value = "invoices/expected-invoices.xml")
    public void invoiceIsAdded() {
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
