package ua.com.foxminded.accountingsystem.service;


import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.service.dto.ClientViewDTO;

import java.util.List;

public interface EmployeeService {


    List<Employee> findAll();

    Employee findOne(long id);

    Employee save(Employee employee);

    void delete(Employee employee);

    void delete(long id);

    List<Client> findAllRelatedClients(Long id);

    List<ClientViewDTO> findRelatedActiveClients(Long id);

}
