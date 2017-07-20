package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.EmployeeFieldValue;

import java.util.List;

public interface EmployeeFieldValueRepository extends JpaRepository<EmployeeFieldValue, Long>{

    List<EmployeeFieldValue> findByEmployeeField_Id(Long id);
}
