package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.model.PaymentType;
import ua.com.foxminded.accountingsystem.service.dto.ClientOfEmployeeDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class EmployeeRepositoryTest extends AbstractRepositoryTest<EmployeeRepository> {

    private static Employee employee;

    @Before
    public void init() {

        employee = new Employee();
        employee.setLastName("Teplinsky");
        employee.setFirstName("Anton");
        employee.setMaxClients(10);
        employee.setCreatedBy("system");
        employee.setCreatedDate(LocalDateTime.now());
    }

    @Test
    @Commit
    @DataSet(value = "employee/empty.xml", disableConstraints = true, cleanBefore = true)
    @ExpectedDataSet(value = "employee/expected-employee.xml", ignoreCols = {"created_by", "created_date"})
    public void addEmployeeTest() {
        repository.save(employee);
    }

    @Test
    @DataSet(value = "employee/stored-employee.xml", disableConstraints = true)
    public void findRelatedActiveClientsTest() {

        ClientOfEmployeeDto expectedClient1 = new ClientOfEmployeeDto(6L, "Andrey", "Vasilenko", 7L, PaymentType.TRIAL, "Personal Mentor", LocalDate.now(),
            null, DealStatus.FROZEN);
        ClientOfEmployeeDto expectedClient2 = new ClientOfEmployeeDto(3L, "Andrey", "Grigorenko", 3L, PaymentType.PREPAY, "Personal Mentor", LocalDate.now(),
            null, DealStatus.FROZEN);

        assertEquals(2, repository.findRelatedActiveClients(2L).size());
        assertThat(repository.findRelatedActiveClients(2L), hasItems(expectedClient1, expectedClient2));
    }

    @Test
    @DataSet(value = "employee/stored-employee.xml", disableConstraints = true)
    public void findAllRelatedClientsTest() {
        assertEquals(6, repository.findAllRelatedClients(2L).size());
    }

    @Test
    @DataSet(value = "employee/stored-employee.xml", disableConstraints = true)
    public void findEmployeeByIdTest() {
        employee.setId(2L);
        assertEquals(employee, repository.findOne(2L));
    }

}
