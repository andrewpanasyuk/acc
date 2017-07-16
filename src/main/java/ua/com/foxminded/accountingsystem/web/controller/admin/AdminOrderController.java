package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderStatus;
import ua.com.foxminded.accountingsystem.service.ClientService;
import ua.com.foxminded.accountingsystem.service.ContractService;
import ua.com.foxminded.accountingsystem.service.OrderService;
import ua.com.foxminded.accountingsystem.service.ServiceService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;
    private final ClientService clientService;
    private final ServiceService service;
    private final ContractService contractService;

    @Autowired
    public AdminOrderController(OrderService orderService, ClientService clientService,
                                ServiceService service, ContractService contractService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.service = service;
        this.contractService = contractService;
    }

    @PostMapping
    public String create(@Valid Order order, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("services", service.findAll());
            return "admin/order";
        }
        orderService.save(order);
        return "redirect:/admin/orders";
    }

    @GetMapping
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.findAll();
        model
            .addAttribute("title", "Orders")
            .addAttribute("orders", orders);
        return "admin/orders";
    }

    @GetMapping(value = "/{id}")
    public String getOrderById(@PathVariable long id, Model model) {
        Order order = orderService.findOne(id);
        model.addAttribute("order", order)
            .addAttribute("title", "Order: " + order.getId())
            .addAttribute("services", service.findAll())
            .addAttribute("contracts", contractService.findAllByOrder(order));
        return "admin/order";
    }

    @DeleteMapping(value = "/{id}")
    public String removeOrder(@PathVariable long id) {
        orderService.delete(orderService.findOne(id));
        return "redirect:/admin/orders";
    }

    @GetMapping(value = "/create")
    public String addOrder(@RequestParam long clientId, Model model) {
        Order order = orderService.createOrderByClientId(clientId);
        model.addAttribute("order", order)
            .addAttribute("services", service.findAll());
        return "admin/order";
    }

    @GetMapping(value = "/new")
    public String placedOrders(Model model) {
        List<Order> orders = orderService.findOrdersByStatus(OrderStatus.NEW);
        model.addAttribute("orders", orders);
        return "admin/orders";
    }
}
