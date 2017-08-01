package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Salary;
import ua.com.foxminded.accountingsystem.service.dto.CashFlowDto;

import java.time.LocalDate;
import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

    @Query("select new ua.com.foxminded.accountingsystem.service.dto.CashFlowDto"
        + "(s.salaryDate, 'SALARY', s.id , si.employeePayment) "
        + "from Salary s inner join s.salaryItems si "
        + "where s.paid = true "
        + "and s.salaryDate >= ?1 and s.salaryDate <= ?2 "
        + "order by s.salaryDate")
    List<CashFlowDto> findAllSalariesBySalaryDateBetween(LocalDate beginDate, LocalDate endDate);

    @Query("select new ua.com.foxminded.accountingsystem.service.dto.CashFlowDto"
        + "(s.salaryDate, 'SALARY', s.id , si.employeePayment) "
        + "from Salary s inner join s.salaryItems si "
        + "where s.paid = true "
        + "and s.salaryDate >= ?1 and s.salaryDate <= ?2 "
        + "and si.invoice.contract.order.service.id = ?3  "
        + "order by s.salaryDate")
    List<CashFlowDto> findServiceSalariesBySalaryDateBetween(LocalDate beginDate, LocalDate endDate, Long serviceId);

}
