package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Salary;

import java.time.LocalDate;
import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

    List<Salary> findSalaryBySalaryDateBetweenOrderBySalaryDate(LocalDate beginDate, LocalDate endDate);

    List<Salary> findSalaryBySalaryDateBetween
}
