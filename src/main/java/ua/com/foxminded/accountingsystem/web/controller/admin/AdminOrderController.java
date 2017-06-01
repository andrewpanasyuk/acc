package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.foxminded.accountingsystem.service.OrderService;
import ua.com.foxminded.accountingsystem.service.UserService;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;

    @Autowired
    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model
            .addAttribute("title", "Users management")
            .addAttribute("orders", orderService.getAllOrders());

        return "admin/orders";
    }

    @GetMapping("/{id}")
    public String getOneOrder(@PathVariable long id, Model model) {
        model
            .addAttribute("title", id + " management")
            .addAttribute("user", orderService.getOrderById(id));
        return "admin/user";
    }

}
