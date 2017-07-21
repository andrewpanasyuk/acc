package ua.com.foxminded.accountingsystem.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.model.User;
import ua.com.foxminded.accountingsystem.service.dto.ClientOfEmployeeDto;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select distinct client from Client client "
           + "inner join client.orders cl_orders, Contract contract "
           + "where contract.order = cl_orders and contract.employee.id = ?1")
    List<Client> findAllRelatedClients(Long employeeId);

    @Query("select distinct new ua.com.foxminded.accountingsystem.service.dto.ClientOfEmployeeDto"
        + "(client.id, client.firstName, client.lastName, contract.order.id, contract.paymentType) "
        + "from Client client inner join client.orders cl_orders, Contract contract "
        + "where contract.order = cl_orders "
        + "and contract.employee.id = ?1 "
        + "and contract.closeType is null "
        + "and contract.order.status = ua.com.foxminded.accountingsystem.model.OrderStatus.ACTIVE")
    List<ClientOfEmployeeDto> findRelatedActiveClients(Long employeeId);

    Employee findByUser_username(String username);

    @Modifying
    @Query(value = "insert into employee_field_value (id, employee_id, employee_field_id, created_by, created_date)\n" +
        "select nextval('employee_field_value_sequence'), e.id, ?1, 'system', now() from employee e", nativeQuery = true)
    void insertEmptyEmployeeFieldValueByEmployeeFieldId(Long id);
}
