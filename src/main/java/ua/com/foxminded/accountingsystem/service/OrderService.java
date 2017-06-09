package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Order;

import java.util.List;

public interface OrderService {

    void removeOrder(Order order);

    Order updateOrder(Order order);

    Order getOrderById(Long id);

    List<Order> getAllOrders();
}
