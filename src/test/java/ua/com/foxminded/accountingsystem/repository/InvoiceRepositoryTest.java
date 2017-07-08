package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class InvoiceRepositoryTest extends AbstractRepositoryTest<InvoiceRepository> {
    @Test
    @DataSet(value = "invoices/stored-invoices_pay_control.xml", disableConstraints = true)
    public void findAllDebtInvoicesTest() {
        assertEquals(2, repository.findDebtInvoices(LocalDate.of(2017, 05, 10)).size());
    }
}
