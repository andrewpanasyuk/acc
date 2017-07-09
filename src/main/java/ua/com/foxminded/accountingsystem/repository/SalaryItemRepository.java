package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.SalaryItem;

public interface SalaryItemRepository extends JpaRepository<SalaryItem, Long> {
}
