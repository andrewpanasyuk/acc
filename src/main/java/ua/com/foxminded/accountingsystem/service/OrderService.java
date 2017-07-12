package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderStatus;

import java.util.List;

public interface OrderService {

    Order createOrderByClientId(Long id);

    void delete(Order order);

    Order save(Order order);

    Order findOne(Long id);

    List<Order> findAll();

    List<Order> getOrdersByStatus(OrderStatus orderStatus);
}
