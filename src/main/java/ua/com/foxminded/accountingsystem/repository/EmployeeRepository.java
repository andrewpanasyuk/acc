package ua.com.foxminded.accountingsystem.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.model.User;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select distinct client from Client client "
           + "inner join client.orders cl_orders, Contract contract "
           + "where contract.order = cl_orders and contract.employee.id = ?1")
    List<Client> findRelatedClients(Long employeeId);
    
    Employee findByUser_username(String username);
}
