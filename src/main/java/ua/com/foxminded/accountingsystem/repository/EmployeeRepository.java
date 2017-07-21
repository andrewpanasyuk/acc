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
    @Query(value = "update employee_field_value set employee_id=e.id, employee_field_id=?1 from employee e", nativeQuery = true)
    void setEmptyEmployeeFieldValueByEmployeeFieldId(Long id);
}
