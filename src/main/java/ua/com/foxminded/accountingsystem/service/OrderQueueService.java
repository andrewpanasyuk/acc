package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderQueue;

import java.util.List;

public interface OrderQueueService {

    OrderQueue create(Order order);

    void delete(OrderQueue orderQueue);

    OrderQueue save(OrderQueue orderQueue);

    OrderQueue findOne(Long id);

    List<OrderQueue> findAll();

    OrderQueue findByOrderId(Long id);

}
