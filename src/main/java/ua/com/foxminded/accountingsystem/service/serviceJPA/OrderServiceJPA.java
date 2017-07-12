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

import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<String> findHistoryByOrder(Order order){

        List<String> notes = new ArrayList<>();
        List<Contract> contracts = contractRepository.findAllByOrderSortedByContractDate(order);

        for (Contract contract: contracts){
            String record = contract.getContractDate() + " - Старт услуги";
            notes.add(record);
            if (contract.getCloseDate() != null && contract.getCloseType() == CloseType.FROZEN){
                record = contract.getCloseDate() + " - Заморозка услуги";
                notes.add(record);
            }else if (contract.getCloseDate() != null && contract.getCloseType() == CloseType.CHANGE){
                record = contract.getCloseDate() + " - Смена ментора";
                notes.add(record);
            }
            else if (contract.getCloseDate() != null && contract.getCloseType() == CloseType.COMPLETED){
                record = contract.getCloseDate() + " - Закрыт договор";
                notes.add(record);
            }

        }

        return notes;
    }
}
