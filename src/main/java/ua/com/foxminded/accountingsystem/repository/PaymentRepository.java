package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
