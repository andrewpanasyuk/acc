package ua.com.foxminded.accountingsystem.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.service.dto.ClientOfEmployeeDto;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select distinct client from Client client "
           + "inner join client.deals cl_deals, Contract contract "
           + "where contract.deal = cl_deals and contract.employee.id = ?1")
    List<Client> findAllRelatedClients(Long employeeId);

    @Query("select distinct new ua.com.foxminded.accountingsystem.service.dto.ClientOfEmployeeDto"
        + "(client.id, client.firstName, client.lastName, contract.deal.id, contract.paymentType) "
        + "from Client client inner join client.deal cl_deals, Contract contract "
        + "where contract.deal = cl_deals "
        + "and contract.employee.id = ?1 "
        + "and contract.closeType is null "
        + "and contract.deal.status = ua.com.foxminded.accountingsystem.model.DealStatus.ACTIVE")
    List<ClientOfEmployeeDto> findRelatedActiveClients(Long employeeId);

    Employee findByUser_username(String username);

}
