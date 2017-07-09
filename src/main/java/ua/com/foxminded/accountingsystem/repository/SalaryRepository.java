package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
}
