package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Employee;

import java.util.List;

/**
 * Created by Dmytro Kushnir on 15.06.17.
 */
public interface EmployeeService {
    List<Employee> findAll();
}
