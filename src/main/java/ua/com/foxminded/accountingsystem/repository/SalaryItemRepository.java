package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.SalaryItem;

import java.time.LocalDate;
import java.util.List;

public interface SalaryItemRepository extends JpaRepository<SalaryItem, Long> {
    @Query("select distinct s from SalaryItem s inner join s.invoice i inner join i.payment " +
        "where s.accounted = false")
    List<SalaryItem> findNotAccountedWithNotPaidInvoices();
}
