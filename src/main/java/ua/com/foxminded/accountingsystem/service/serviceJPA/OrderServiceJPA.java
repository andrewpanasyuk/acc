package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.CloseType;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderStatus;
import ua.com.foxminded.accountingsystem.repository.ClientRepository;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;
import ua.com.foxminded.accountingsystem.repository.OrderRepository;
import ua.com.foxminded.accountingsystem.service.OrderService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceJPA implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ContractRepository contractRepository;

    @Autowired
    public OrderServiceJPA(OrderRepository orderRepository, ClientRepository clientRepository,
                           ContractRepository contractRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.contractRepository = contractRepository;
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

    @Override
    public List<Order> findOrdersByStatus(OrderStatus orderStatus) {
        return orderRepository.findOrdersByStatus(orderStatus);
    }

    @Override
    public void close(Order order, OrderStatus orderStatus) {
        order.setStatus(orderStatus);
        order.setCloseDate(LocalDate.now());
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void freeze(Long id) {
        Order order = orderRepository.findOne(id);
        order.setStatus(OrderStatus.FROZEN);
        Contract contract = contractRepository.findContractByOrderIdAndCloseTypeIsNull(id);
        contract.setCloseType(CloseType.FROZEN);
        contract.setCloseDate(LocalDate.now());
        contract.setClosingDescription("freeze");
        contractRepository.save(contract);
        orderRepository.save(order);
    }
}
