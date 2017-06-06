package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Order;
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

    @GetMapping("/{id}")
    public String getOrderByID(@PathVariable long id, Model model) {
        model
            .addAttribute("order", orderService.getOrderById(id));
        return "admin/order";
    }


    @PostMapping(value = "/update")
    public String update(@ModelAttribute Order order) {
        orderService.updateOrder(order);
        return "redirect:/admin/orders";
    }

    @GetMapping(value = "/{id}/order")
    public String edit(@PathVariable long id,
                       Model model) {
        Order order = orderService.getOrderById(id);
        Client client = order.getClient();

        model.addAttribute("order", order)
            .addAttribute("client", client);
        return "admin/order";
    }


    @GetMapping(value = "/{id}/remove")
    public String removeOrder(@PathVariable long id) {
        Order order = orderService.getOrderById(id);
        orderService.removeOrder(order);
        return "redirect:/admin/orders";
    }

    @GetMapping(value = "/orderAdd")
    public String addOrder(Model model) {
        Order order = new Order();
        order.setOpenDate(LocalDate.now());
        System.out.println(order);
        model.addAttribute("order", order);
        return "admin/order";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Order order) {
        orderService.addOrder(order);
        return "redirect:/admin/orders";
    }

}
