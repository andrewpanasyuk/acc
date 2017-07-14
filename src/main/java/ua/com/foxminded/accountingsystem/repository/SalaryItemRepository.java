package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.SalaryItem;

import java.time.LocalDate;
import java.util.List;

public interface SalaryItemRepository extends JpaRepository<SalaryItem, Long> {

    @Query("select distinct s from SalaryItem s where s.accounted = false and s.dateFrom >= ?1 and s.dateTo <= ?2")
    List<SalaryItem> findByDatePeriod(LocalDate dateFrom, LocalDate dateTo);
}
