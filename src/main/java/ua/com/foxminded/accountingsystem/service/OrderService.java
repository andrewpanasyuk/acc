package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Order;

import java.util.List;

/**
 * Created by Andrew on 01.06.2017.
 */
public interface OrderService {
    void addOrder(Order order);
    void removeOrder(Order order);
    Order updateOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
}
