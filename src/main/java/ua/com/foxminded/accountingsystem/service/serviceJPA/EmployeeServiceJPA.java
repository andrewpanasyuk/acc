package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.repository.EmployeeRepository;
import ua.com.foxminded.accountingsystem.service.EmployeeService;

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
    public List<Client> findRelatedClients(Long id) {
       return employeeRepository.findRelatedClients(id);
    }
}
