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
import ua.com.foxminded.accountingsystem.service.OrderService;

import java.time.LocalDate;
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
    public OrderQueue save(OrderQueue orderQueue) {
        return orderQueueRepository.save(orderQueue);
    }

    @Override
    public OrderQueue findOne(Long id) {
        return orderQueueRepository.findOne(id);
    }

    @Override
    public List<OrderQueue> findAll() {
        return orderQueueRepository.findAll();
    }

    @Override
    public OrderQueue findQueueItemByOrder(Order order) {
        return orderQueueRepository.findQueueItemByOrder(order);
    }

    public OrderQueue createQueueItemByOrderId(Long id) {
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
        save(orderQueue);
        return orderQueue;
    }
}
