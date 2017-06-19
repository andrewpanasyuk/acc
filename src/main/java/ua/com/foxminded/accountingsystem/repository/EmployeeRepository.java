package ua.com.foxminded.accountingsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
