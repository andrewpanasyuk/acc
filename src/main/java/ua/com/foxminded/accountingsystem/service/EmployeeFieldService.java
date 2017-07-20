package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.EmployeeField;

import java.util.List;

public interface EmployeeFieldService {
   List<EmployeeField> findAll();

    void delete(Long id);

    EmployeeField create(EmployeeField employeeField);

}
