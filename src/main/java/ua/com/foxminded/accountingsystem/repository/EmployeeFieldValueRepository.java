package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.EmployeeFieldValue;

public interface EmployeeFieldValueRepository extends JpaRepository<EmployeeFieldValue, Long>{

    void deleteByEmployeeField_Id(Long id);

    @Modifying
    @Query(value = "insert into employee_field_value (id, employee_id, employee_field_id, created_by, created_date, " +
        "last_modified_by, last_modified_date) select nextval('employee_field_value_sequence'), e.id, ?1, 'system', now(), " +
        "'system', now() from employee e", nativeQuery = true)
    void insertForAllEmployeesByEmployeeFieldId(Long id);
}
