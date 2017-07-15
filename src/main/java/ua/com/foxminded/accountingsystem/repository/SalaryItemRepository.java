package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.SalaryItem;

import java.time.LocalDate;
import java.util.List;

public interface SalaryItemRepository extends JpaRepository<SalaryItem, Long> {
    List<SalaryItem> findByInvoice_PaymentIdNotNullAndAccountedIsFalseAndDateToLessThan(LocalDate dateTo);
}
