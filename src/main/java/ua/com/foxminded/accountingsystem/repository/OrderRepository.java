package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
}
