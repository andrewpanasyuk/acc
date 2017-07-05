package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.Employee;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;

public class EmployeeRepositoryTest extends AbstractRepositoryTest<EmployeeRepository> {
    private Employee employee;

    @Before
    public  void init() {
        employee = new Employee("john","doe",100, new ArrayList<>());
        employee.setId(50L);
    }

    @Test
    @Commit
    @DataSet(value = "employee/empty.xml", cleanBefore = true)
    @ExpectedDataSet("employee/expected-employee.xml")
    public void addEmployee() {
        repository.save(employee);
    }

    @Test
    @Commit
    @DataSet(value = "employee/stored-employee.xml")
    public void testFindAllEmployee() {
        assertEquals(2, repository.findAll().size());
    }

    @Test
    @Commit
    @DataSet(value = "employee/expected-employee.xml", cleanBefore = true, disableConstraints = true)
    public void testFindObjectEqualToObjectInDb( ){
        assertEquals(repository.findOne(50L) ,employee);
    }

    @Test
    @Commit
    @DataSet(value = "employee/stored-employee.xml")
    public void testFindOneEmployeeById() {
        assertEquals("john", repository.findOne(50L).getFirstName());
    }

    @Test
    @Commit
    @DataSet(value = "employee/stored-employee.xml")
    public void ifEmployeeNotFoundByIdReturnNull() {
        assertThat(repository.findOne(60L), nullValue());
    }

    @Test
    @Commit
    @DataSet(value = "employee/stored-employee.xml", cleanBefore = true)
    @ExpectedDataSet("employee/expected-employee.xml")
    public void deleteEmployeeById() {
        repository.delete(51L);
    }

}
