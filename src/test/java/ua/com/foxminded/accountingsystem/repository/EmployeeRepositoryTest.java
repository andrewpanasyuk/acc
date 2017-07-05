package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.model.PaymentType;
import ua.com.foxminded.accountingsystem.service.dto.ClientOfEmployeeDto;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;


public class EmployeeRepositoryTest extends AbstractRepositoryTest<EmployeeRepository> {

    private static Employee employee;

    @Before
    public void init() {

        employee = new Employee();
        employee.setId(2L);
        employee.setLastName("Teplinsky");
        employee.setFirstName("Anton");
        employee.setMaxClients(10);
    }

    @Test
    @Commit
    @DataSet(value = "employee/empty.xml", disableConstraints = true, cleanBefore = true)
    @ExpectedDataSet("employee/expected-employee.xml")
    public void addEmployeeTest() {
        repository.save(employee);
    }

    @Test
    @DataSet(value = "employee/stored-employee.xml", disableConstraints = true)
    public void countRelatedActiveClientsTest() {
        assertEquals(2, repository.findRelatedActiveClients(2L).size());
    }

    @Test
    @DataSet(value = "employee/stored-employee.xml", disableConstraints = true)
    public void checkRelatedActiveClientsTest() {

        List<ClientOfEmployeeDto> expectedClients = new ArrayList<>();
        expectedClients.add(new ClientOfEmployeeDto(6L,"Andrey", "Vasilenko", 7L, PaymentType.TRIAL));
        expectedClients.add(new ClientOfEmployeeDto(3L,"Andrey", "Grigorenko", 3L, PaymentType.PREPAY));

        List<ClientOfEmployeeDto> activeClients = repository.findRelatedActiveClients(2L);

        assertEquals(expectedClients.get(0), activeClients.get(0));
        assertEquals(expectedClients.get(1), activeClients.get(1));
    }

    @Test
    @DataSet(value = "employee/stored-employee.xml", disableConstraints = true)
    public void findAllRelatedClientsTest() {
        assertEquals(5, repository.findAllRelatedClients(2L).size());
    }

    @Test
    @DataSet(value = "employee/stored-employee.xml" , disableConstraints = true)
    public void findEmployeeByIdTest() {
        assertEquals(employee, repository.findOne(2L));
    }

}
