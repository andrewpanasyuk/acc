package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderQueue;
import ua.com.foxminded.accountingsystem.model.OrderStatus;
import ua.com.foxminded.accountingsystem.model.Service;

import java.util.List;
import java.util.Map;

public interface OrderQueueService {

    OrderQueue createQueueByOrderId(Long id);

    void delete(OrderQueue orderQueue);

    void leaveQueue(Long id, OrderStatus cause);

    OrderQueue save(OrderQueue orderQueue);

    OrderQueue findOne(Long id);

    List<OrderQueue> findAll();

    Map<Service, List<OrderQueue>> findAllGroupByService();

    OrderQueue findQueueByOrder(Order order);

}
