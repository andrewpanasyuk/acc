package ua.com.foxminded.accountingsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select distinct c from Client c "
           + "inner join fetch c.orders o, Contract cntr "
           + "where cntr.order = o and cntr.employee.id = ?1")
    List<Client> findRelatedClients(Long employeeId);
}
