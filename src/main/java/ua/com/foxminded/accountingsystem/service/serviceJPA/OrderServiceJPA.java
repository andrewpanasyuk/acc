package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderStatus;
import ua.com.foxminded.accountingsystem.repository.ClientRepository;
import ua.com.foxminded.accountingsystem.repository.OrderRepository;
import ua.com.foxminded.accountingsystem.service.OrderService;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceJPA implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public OrderServiceJPA(OrderRepository orderRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Order createOrderByClientId(Long id) {
        Client client = clientRepository.findOne(id);
        Order order = new Order();
        order.setClient(client);
        order.setStatus(OrderStatus.NEW);
        client.getOrders().add(order);
        order.setOpenDate(LocalDate.now());
        return order;
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOne(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
