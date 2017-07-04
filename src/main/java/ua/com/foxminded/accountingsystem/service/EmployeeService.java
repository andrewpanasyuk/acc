package ua.com.foxminded.accountingsystem.service;


import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Employee;

import java.util.List;

public interface EmployeeService {


    List<Employee> findAll();

    Employee findOne(long id);

    Employee save(Employee employee);

    void delete(Employee employee);

    void delete(long id);

    List<Client> findRelatedClients(Long id);
    
    Employee findByUsername(String username);

}
