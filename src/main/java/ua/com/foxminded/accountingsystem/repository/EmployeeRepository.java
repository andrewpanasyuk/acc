package ua.com.foxminded.accountingsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select distinct client from Client client "
           + "inner join client.orders cl_orders, Contract contract "
           + "where contract.order = cl_orders and contract.employee.id = ?1")
    List<Client> findAllRelatedClients(Long employeeId);

    @Query("select distinct client, contract from Client client "
        + "inner join client.orders cl_orders, Contract contract "
        + "where contract.order = cl_orders "
        + "and contract.employee.id = ?1 "
        + "and contract.closeType is null "
        + "and contract.order.status = ua.com.foxminded.accountingsystem.model.OrderStatus.ACTIVE")
    List<Object[]> findRelatedActiveClientsAndContracts(Long employeeId);
}
