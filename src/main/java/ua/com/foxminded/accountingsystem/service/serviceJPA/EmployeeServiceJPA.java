package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.repository.EmployeeRepository;
import ua.com.foxminded.accountingsystem.service.EmployeeService;
import ua.com.foxminded.accountingsystem.service.dto.ClientOfEmployeeDto;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceJPA implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceJPA(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findOne(long id) {
        return employeeRepository.findOne(id);
    }


    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);

    }

    @Override
    public void delete(long id) {
        employeeRepository.delete(id);
    }

    @Override
    public List<ClientOfEmployeeDto> findAllRelatedClients(Long id) {
        List<ClientOfEmployeeDto> allRelatedClients = employeeRepository.findAllRelatedClients(id);
        Collections.sort(allRelatedClients);
        return allRelatedClients;
    }

    @Override
    public List<ClientOfEmployeeDto> findRelatedActiveClients(Long id) {
        return employeeRepository.findRelatedActiveClients(id);
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUser_username(username);
    }
}
