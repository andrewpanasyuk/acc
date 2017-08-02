package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderQueue;
import ua.com.foxminded.accountingsystem.model.OrderStatus;
import ua.com.foxminded.accountingsystem.model.Priority;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;
import ua.com.foxminded.accountingsystem.repository.OrderQueueRepository;
import ua.com.foxminded.accountingsystem.repository.OrderRepository;
import ua.com.foxminded.accountingsystem.repository.ServiceRepository;
import ua.com.foxminded.accountingsystem.service.OrderQueueService;
import ua.com.foxminded.accountingsystem.service.OrderService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderQueueServiceJPA implements OrderQueueService {

    private final OrderQueueRepository orderQueueRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final ContractRepository contractRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public OrderQueueServiceJPA(OrderQueueRepository orderQueueRepository, OrderRepository orderRepository,
                                ContractRepository contractRepository, ServiceRepository serviceRepository,
                                OrderService orderService) {
        this.orderQueueRepository = orderQueueRepository;
        this.orderRepository = orderRepository;
        this.contractRepository = contractRepository;
        this.serviceRepository = serviceRepository;
        this.orderService = orderService;
    }

    @Override
    @Transactional
    public void delete(OrderQueue orderQueue) {
        Order order = orderQueue.getOrder();
        List<Contract> contracts = contractRepository.findAllByOrderOrderByContractDateDesc(order);
        if(contracts.isEmpty()){
            order.setStatus(OrderStatus.NEW);
        } else {
            order.setStatus(OrderStatus.FROZEN);
        }
        orderRepository.save(order);
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
    public Map<ua.com.foxminded.accountingsystem.model.Service, List<OrderQueue>> findAllGroupByService() {
        Map<ua.com.foxminded.accountingsystem.model.Service, List<OrderQueue>> queuesByService = new HashMap<>();
        List<OrderQueue> orderQueues = orderQueueRepository.findAll();
        Collections.sort(orderQueues);
        List<ua.com.foxminded.accountingsystem.model.Service> services = serviceRepository.findAll();
        services.forEach(service -> queuesByService.put(service, new ArrayList<>()));
        orderQueues.forEach(orderQueue -> queuesByService.get(orderQueue.getOrder().getService()).add(orderQueue));
        return queuesByService;

    }

    @Override
    public OrderQueue findQueueByOrder(Order order) {
        return orderQueueRepository.findByOrder(order);
    }

    @Transactional
    @Override
    public void refuse(Long id) {
        OrderQueue orderQueue = orderQueueRepository.findOne(id);
        Order order = orderQueue.getOrder();
        orderQueueRepository.delete(orderQueue);
        orderService.refuse(order.getId());
    }

    @Transactional
    @Override
    public void reject(Long id) {
        OrderQueue orderQueue = orderQueueRepository.findOne(id);
        Order order = orderQueue.getOrder();
        orderQueueRepository.delete(orderQueue);
        orderService.reject(order.getId());
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
