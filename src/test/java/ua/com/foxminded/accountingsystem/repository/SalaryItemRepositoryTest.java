package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.SalaryItem;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;

public class SalaryItemRepositoryTest extends AbstractRepositoryTest<SalaryItemRepository> {
    private static SalaryItem salaryItem_1;
    private static SalaryItem salaryItem_2;

    @BeforeClass
    public static void init() {
        Employee employee_1 = new Employee();
        employee_1.setId(50L);
        Employee employee_2 = new Employee();
        employee_2.setId(51L);
        Invoice invoice_1 = new Invoice();
        invoice_1.setId(50L);
        Invoice invoice_2 = new Invoice();
        invoice_2.setId(51L);
        Money money_1 = new Money(1500, Currency.UAH);
        Money money_2 = new Money(4500, Currency.UAH);

        salaryItem_1 = new SalaryItem(employee_1, invoice_1, money_1, LocalDate.of(2010, 1, 1), LocalDate.of(2010, 1, 1).plusMonths(1L));
        salaryItem_2 = new SalaryItem(employee_2, invoice_2, money_2, LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 1).plusMonths(1L));
    }

    @Test
    @Commit
    @DataSet(value = "salary-items/empty.xml", disableConstraints = true, cleanBefore = true)
    @ExpectedDataSet(value = "salary-items/expected-salary-items.xml", ignoreCols = {"salary_id"})
    public void twoSalaryItemAreAdded() {
        repository.save(salaryItem_1);
        repository.save(salaryItem_2);
    }

    @Test
    @DataSet(value = "salary-items/stored-salary-items.xml", disableConstraints = true)
    public void repositoryContainTwoSalaryItems() {
        assertEquals(2, repository.findAll().size());
    }

    @Test
    @DataSet(value = "salary-items/stored-salary-items.xml", disableConstraints = true)
    public void ifNoSalaryItemFoundReturnNull() {
        assertThat(repository.findOne(666L), nullValue());
    }

    @Test
    @DataSet(value = "salary-items/empty.xml", disableConstraints = true, cleanBefore = true)
    public void ifNoSalaryItemsFoundReturnEmptyList() {
        assertThat(repository.findAll(), is(empty()));
    }

    @Test
    @Commit
    @DataSet(value = "salary-items/stored-salary-items.xml", disableConstraints = true)
    @ExpectedDataSet(value = "salary-items/empty.xml")
    public void salaryItemsRemovedAndRepoIsEmpty() {
        repository.deleteAll();
        assertEquals(0, repository.findAll().size());
    }
}
