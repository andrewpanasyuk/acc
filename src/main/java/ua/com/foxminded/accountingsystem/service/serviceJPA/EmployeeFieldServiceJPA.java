package ua.com.foxminded.accountingsystem.service.serviceJPA;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.model.EmployeeField;
import ua.com.foxminded.accountingsystem.model.EmployeeFieldValue;
import ua.com.foxminded.accountingsystem.repository.EmployeeFieldRepository;
import ua.com.foxminded.accountingsystem.repository.EmployeeFieldValueRepository;
import ua.com.foxminded.accountingsystem.repository.EmployeeRepository;
import ua.com.foxminded.accountingsystem.service.EmployeeFieldService;

import java.util.List;

@Service
public class EmployeeFieldServiceJPA implements EmployeeFieldService {

    private final EmployeeFieldRepository employeeFieldRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeFieldValueRepository employeeFieldValueRepository;

    @Autowired
    public EmployeeFieldServiceJPA(EmployeeFieldRepository employeeFieldRepository, EmployeeRepository employeeRepository,
                                   EmployeeFieldValueRepository employeeFieldValueRepository) {
        this.employeeFieldRepository = employeeFieldRepository;
        this.employeeRepository = employeeRepository;
        this.employeeFieldValueRepository = employeeFieldValueRepository;
    }

    @Override
    public List<EmployeeField> findAll() {
        return employeeFieldRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        List<EmployeeFieldValue> employeeFieldValues = employeeFieldValueRepository.findByEmployeeField_Id(id);
        employeeFieldValues.forEach(employeeFieldValue -> {
            Employee employee = employeeFieldValue.getEmployee();
            employee.removeEmployeeFieldValue(employeeFieldValue);
            employeeRepository.save(employee);
        });
        employeeFieldRepository.delete(id);
    }

    @Override
    public EmployeeField create(EmployeeField employeeField) {
        EmployeeField savedField = employeeFieldRepository.save(employeeField);
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(employee -> {
            EmployeeFieldValue employeeFieldValue = new EmployeeFieldValue();
            employeeFieldValue.setEmployeeField(savedField);
            employee.addEmployeeFieldValue(employeeFieldValue);
            employeeRepository.save(employee);
        });
        return savedField;
    }
}
