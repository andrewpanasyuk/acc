package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.EmployeeField;

public interface EmployeeFieldRepository extends JpaRepository<EmployeeField, Long> {
}
