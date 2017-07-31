package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderStatus;
import ua.com.foxminded.accountingsystem.model.Service;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{

    List<Order> findOrdersByStatus(OrderStatus orderStatus);

    long countOrdersByStatusAndService(OrderStatus orderStatus, Service service);

}
