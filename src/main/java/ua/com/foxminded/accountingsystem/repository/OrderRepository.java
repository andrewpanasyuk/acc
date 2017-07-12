package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderStatus;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{

    @Query("SELECT orders FROM Order orders WHERE orders.status = ?1")
    List<Order> getOrdersByStatus(OrderStatus orderStatus);
}
