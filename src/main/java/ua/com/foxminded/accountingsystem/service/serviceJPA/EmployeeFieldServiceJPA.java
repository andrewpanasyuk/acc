package ua.com.foxminded.accountingsystem.service.serviceJPA;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.EmployeeField;
import ua.com.foxminded.accountingsystem.repository.EmployeeFieldRepository;
import ua.com.foxminded.accountingsystem.service.EmployeeFieldService;

import java.util.List;

@Service
public class EmployeeFieldServiceJPA implements EmployeeFieldService {

    private final EmployeeFieldRepository employeeFieldRepository;

    @Autowired
    public EmployeeFieldServiceJPA(EmployeeFieldRepository employeeFieldRepository) {
        this.employeeFieldRepository = employeeFieldRepository;
    }

    @Override
    public List<EmployeeField> findAll() {
        return employeeFieldRepository.findAll();
    }
}
