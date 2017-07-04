package ua.com.foxminded.accountingsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.service.dto.ClientOfEmployeeDto;

import java.util.List;

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
}
