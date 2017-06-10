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

    @GetMapping
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model
            .addAttribute("orders", orders);
        return "admin/orders";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute Order order) {
        orderService.updateOrder(order);
        return "redirect:/admin/orders";
    }

    @GetMapping(value = "/{id}")
    public String getOrderById(@PathVariable long id,
                       Model model) {
        Order order = orderService.getOrderById(id);
        Client client = order.getClient();
        model.addAttribute("order", order)
            .addAttribute("client", client)
            .addAttribute("localDate", LocalDate.now())
            .addAttribute("statuses", OrderStatus.values());
        return "admin/order";
    }

    @PostMapping(value = "/remove")
    public String removeOrder(@ModelAttribute Order order) {
        orderService.removeOrder(order);
        return "redirect:/admin/orders";
    }

    @PostMapping(value = "/addOrder")
    public String addOrder(Client client, Model model) {
        Order order = new Order();
        order.setClient(client);
        client.getOrders().add(order);
        order.setOpenDate(LocalDate.now());
        model.addAttribute("order", order)
            .addAttribute("statuses", OrderStatus.values())
            .addAttribute("client", client);
        return "admin/order";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute Order order) {
        orderService.updateOrder(order);
        return "redirect:/admin/orders";
    }

}
