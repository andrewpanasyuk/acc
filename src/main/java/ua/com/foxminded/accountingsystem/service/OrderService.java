package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Order;

import java.util.List;

public interface OrderService {

    void delete(Order order);

    Order save(Order order);

    Order findOne(Long id);

    List<Order> findAll();
}
