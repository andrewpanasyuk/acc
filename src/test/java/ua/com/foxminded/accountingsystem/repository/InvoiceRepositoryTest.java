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

public class InvoiceRepositoryTest extends AbstractRepositoryTest<InvoiceRepository> {

    private static Invoice firstInvoiceOfContractOne;
    private static Invoice secondInvoiceOfContractOne;
    private static Invoice invoiceOfContractTwo;

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

        firstInvoiceOfContractOne = new Invoice();
        firstInvoiceOfContractOne.setId(1L);
        firstInvoiceOfContractOne.setContract(contract1);
        firstInvoiceOfContractOne.setCreationDate(contract1.getPaymentDate());
        firstInvoiceOfContractOne.setPrice(moneyOfContractOne);
        firstInvoiceOfContractOne.setPaymentPeriodFrom(contract1.getPaymentDate());
        firstInvoiceOfContractOne.setPaymentPeriodTo(contract1.getPaymentDate().plusMonths(1L));

        secondInvoiceOfContractOne = new Invoice();
        secondInvoiceOfContractOne.setId(2L);
        secondInvoiceOfContractOne.setContract(contract1);
        secondInvoiceOfContractOne.setCreationDate(contract1.getPaymentDate().plusMonths(1L));
        secondInvoiceOfContractOne.setPrice(moneyOfContractOne);
        secondInvoiceOfContractOne.setPaymentPeriodFrom(contract1.getPaymentDate().plusMonths(1L));
        secondInvoiceOfContractOne.setPaymentPeriodTo(contract1.getPaymentDate().plusMonths(2L));

        invoiceOfContractTwo = new Invoice();
        invoiceOfContractTwo.setContract(contract2);
        invoiceOfContractTwo.setCreationDate(contract2.getPaymentDate());
        invoiceOfContractTwo.setPrice(moneyOfContractTwo);
        invoiceOfContractTwo.setPaymentPeriodFrom(contract2.getPaymentDate());
        invoiceOfContractTwo.setPaymentPeriodTo(contract2.getPaymentDate().plusMonths(1L));
        invoiceOfContractTwo.setEmployeePaid(true);
    }


    @Test
    @Commit
    @DataSet(value = "invoices/empty.xml", disableConstraints = true)
    @ExpectedDataSet(value = "invoices/expected-invoices.xml")
    public void OneInvoiceIsAdded() {
        repository.save(invoiceOfContractTwo);
    }

}
