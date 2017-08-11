package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Salary;
import ua.com.foxminded.accountingsystem.model.SalaryItem;

import java.time.LocalDate;
import java.util.List;

public interface SalaryService {

    List<Salary> prepareSalaries(LocalDate dateFrom, LocalDate dateTo);

    Salary create(Salary salary);

    List<Salary> findAllSalary();

    List<SalaryItem> findAllSalaryItemBySalaryId(Long id);
}
