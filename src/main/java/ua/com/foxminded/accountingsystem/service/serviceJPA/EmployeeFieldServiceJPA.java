package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.accountingsystem.model.EmployeeField;
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
    @Transactional
    public void delete(Long id) {
        employeeFieldValueRepository.deleteByEmployeeField_Id(id);
        employeeFieldRepository.delete(id);
    }

    @Override
    @Transactional
    public EmployeeField create(EmployeeField employeeField) {
        EmployeeField savedField = employeeFieldRepository.saveAndFlush(employeeField);
        employeeRepository.setEmptyEmployeeFieldValueByEmployeeFieldId(savedField.getId());
        return savedField;
    }
}
