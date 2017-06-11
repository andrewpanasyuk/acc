package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderStatus;
import ua.com.foxminded.accountingsystem.service.ClientService;
import ua.com.foxminded.accountingsystem.service.OrderService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;
    private final ClientService clientService;

    @Autowired
    public AdminOrderController(OrderService orderService, ClientService clientService) {
        this.orderService = orderService;
        this.clientService = clientService;
    }

    @PostMapping
    public String create(@ModelAttribute Order order) {
        orderService.save(order);
        return "redirect:/admin/orders";
    }

    @GetMapping
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "admin/orders";
    }

    @GetMapping(value = "/{id}")
    public String getOrderById(@PathVariable long id,
                       Model model) {
        Order order = orderService.findOne(id);
        model.addAttribute("order", order)
            .addAttribute("localDate", LocalDate.now())
            .addAttribute("statuses", OrderStatus.values());
        return "admin/order";
    }

    @PostMapping(value = "/remove")
    public String removeOrder(@ModelAttribute Order order) {
        orderService.delete(order);
        return "redirect:/admin/orders";
    }

    @GetMapping(value = "/new")
    public String addOrder(@RequestParam long clientId, Model model) {
        Order order = new Order();
        Client client = clientService.findOne(clientId);
        order.setClient(client);
        client.getOrders().add(order);
        order.setOpenDate(LocalDate.now());
        model.addAttribute("order", order)
            .addAttribute("statuses", OrderStatus.values())
            .addAttribute("client", client);
        return "admin/order";
    }

}
