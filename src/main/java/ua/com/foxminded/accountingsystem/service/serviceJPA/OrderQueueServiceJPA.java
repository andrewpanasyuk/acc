package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.OrderQueue;
import ua.com.foxminded.accountingsystem.repository.OrderQueueRepository;
import ua.com.foxminded.accountingsystem.service.OrderQueueService;

import java.util.List;

@Service
public class OrderQueueServiceJPA implements OrderQueueService {

    private final OrderQueueRepository orderQueueRepository;

    @Autowired
    public OrderQueueServiceJPA(OrderQueueRepository orderQueueRepository) {
        this.orderQueueRepository = orderQueueRepository;
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
}
