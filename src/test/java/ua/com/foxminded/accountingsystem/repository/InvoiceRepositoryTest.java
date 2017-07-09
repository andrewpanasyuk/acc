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

public class InvoiceRepositoryTest extends AbstractRepositoryTest<InvoiceRepository> {

    private static Invoice invoice_1;
    private static Invoice invoice_2;

    @BeforeClass
    public static void init() {
        Money moneyOfContractOne = new Money();
        moneyOfContractOne.setId(1L);
        moneyOfContractOne.setCurrency(Currency.EUR);
        moneyOfContractOne.setPrice(1500);
        Money moneyOfContractTwo = new Money();
        moneyOfContractTwo.setId(2L);
        moneyOfContractTwo.setCurrency(Currency.EUR);
        moneyOfContractTwo.setPrice(2000);

        Contract contract1 = new Contract();
        contract1.setId(1L);
        contract1.setPaymentDate(LocalDate.of(2010, 1, 1));

        Contract contract2 = new Contract();
        contract2.setId(2L);
        contract2.setPaymentDate(LocalDate.of(2016, 1, 1));

        invoice_1 = new Invoice();
        invoice_1.setContract(contract1);
        invoice_1.setCreationDate(contract1.getPaymentDate());
        invoice_1.setPrice(moneyOfContractOne);
        invoice_1.setPaymentPeriodFrom(contract1.getPaymentDate());
        invoice_1.setPaymentPeriodTo(contract1.getPaymentDate().plusMonths(1L));

        invoice_2 = new Invoice();
        invoice_2.setContract(contract1);
        invoice_2.setCreationDate(contract1.getPaymentDate().plusMonths(1L));
        invoice_2.setPrice(moneyOfContractOne);
        invoice_2.setPaymentPeriodFrom(contract1.getPaymentDate().plusMonths(1L));
        invoice_2.setPaymentPeriodTo(contract1.getPaymentDate().plusMonths(2L));
        invoice_2.setEmployeePaid(true);
    }


    @Test
    @Commit
    @DataSet(value = "invoices/empty.xml", disableConstraints = true, cleanBefore = true)
    @ExpectedDataSet(value = "invoices/expected-invoices.xml")
    public void twoInvoicesAreAdded() {
        repository.save(invoice_1);
        repository.save(invoice_2);
    }

}
