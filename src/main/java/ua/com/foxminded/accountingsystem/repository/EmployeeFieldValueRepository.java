package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.EmployeeFieldValue;

public interface EmployeeFieldValueRepository extends JpaRepository<EmployeeFieldValue, Long>{

    void deleteByEmployeeField_Id(Long id);
}
