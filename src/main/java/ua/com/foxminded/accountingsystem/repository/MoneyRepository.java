package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Money;

public interface MoneyRepository extends JpaRepository<Money, Long> {
}
