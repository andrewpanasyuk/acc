package ua.com.foxminded.accountingsystem.service;

import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderQueue;

import java.util.List;

public interface OrderQueueService {

    void delete(OrderQueue orderQueue);

    OrderQueue save(OrderQueue orderQueue);

    OrderQueue findOne(Long id);

    List<OrderQueue> findAll();

    OrderQueue findQueueByOrderId(Long id);
}
