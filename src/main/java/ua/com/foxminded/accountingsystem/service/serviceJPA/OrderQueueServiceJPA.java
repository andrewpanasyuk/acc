package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderQueue;
import ua.com.foxminded.accountingsystem.model.OrderStatus;
import ua.com.foxminded.accountingsystem.model.Priority;
import ua.com.foxminded.accountingsystem.repository.OrderQueueRepository;
import ua.com.foxminded.accountingsystem.repository.OrderRepository;
import ua.com.foxminded.accountingsystem.service.OrderQueueService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class OrderQueueServiceJPA implements OrderQueueService {

    private final OrderQueueRepository orderQueueRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderQueueServiceJPA(OrderQueueRepository orderQueueRepository, OrderRepository orderRepository) {
        this.orderQueueRepository = orderQueueRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void delete(OrderQueue orderQueue) {
        orderQueueRepository.delete(orderQueue);
    }

    @Override
    public void delete(Long id) {
        orderQueueRepository.delete(id);
    }

    @Override
    public OrderQueue save(OrderQueue orderQueue) {
        return orderQueueRepository.save(orderQueue);
    }

    @Override
    public OrderQueue findOne(Long id) {
        return orderQueueRepository.findOne(id);
    }

    @Override
    public List<OrderQueue> findAll() {
        List<OrderQueue> orderQueues = orderQueueRepository.findAll();
        Collections.sort(orderQueues);
        return orderQueues;
    }

    @Override
    public OrderQueue findQueueByOrder(Order order) {
        return orderQueueRepository.findByOrder(order);
    }

    public OrderQueue createQueueByOrderId(Long id) {
        Order order = orderRepository.findOne(id);
        OrderQueue orderQueue = new OrderQueue();
        orderQueue.setQueuingDate(LocalDate.now());
        if (order.getStatus().equals(OrderStatus.NEW)){
            orderQueue.setPriority(Priority.NORMAL);
        } else {
            orderQueue.setPriority(Priority.HIGH);
        }
        order.setStatus(OrderStatus.WAITING);
        orderQueue.setOrder(order);
        orderQueueRepository.save(orderQueue);
        return orderQueue;
    }
}
