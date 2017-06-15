package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Employee;

/**
 * Created by Dmytro Kushnir on 15.06.17.
 */
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {
}
