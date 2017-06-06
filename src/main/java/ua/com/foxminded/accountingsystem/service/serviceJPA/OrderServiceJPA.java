package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.repository.OrderRepository;
import ua.com.foxminded.accountingsystem.service.OrderService;

import java.util.List;

@Service
public class OrderServiceJPA implements OrderService {
    OrderRepository orderRepository;
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
    }
    @Override
    public void removeOrder(Order order) {
        orderRepository.delete(order);
    }
    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }
    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findOne(id);
    }
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
