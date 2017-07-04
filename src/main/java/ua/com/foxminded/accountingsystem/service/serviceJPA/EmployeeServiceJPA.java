package ua.com.foxminded.accountingsystem.service.serviceJPA;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.model.User;
import ua.com.foxminded.accountingsystem.repository.EmployeeRepository;
import ua.com.foxminded.accountingsystem.repository.UserRepository;
import ua.com.foxminded.accountingsystem.service.EmployeeService;

@Service
public class EmployeeServiceJPA implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;


    @Autowired
    public EmployeeServiceJPA(EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
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

    @Override
    public Employee findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
            return null;
        }
        
        return employeeRepository.findByUser(user);
    }
}
