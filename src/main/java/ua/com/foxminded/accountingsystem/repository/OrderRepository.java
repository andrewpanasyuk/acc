package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Order;

/**
 * Created by Andrew on 01.06.2017.
 */
public interface OrderRepository extends JpaRepository<Order, Long>{
}
