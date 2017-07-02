package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderQueue;
import ua.com.foxminded.accountingsystem.service.OrderQueueService;
import ua.com.foxminded.accountingsystem.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/admin/queues")
public class AdminOrderQueueController {

    private final OrderQueueService orderQueueService;
    private final OrderService orderService;

    @Autowired
    public AdminOrderQueueController(OrderQueueService orderQueueService, OrderService orderService) {
        this.orderQueueService = orderQueueService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getQueues(Model model) {
        List<OrderQueue> queues = orderQueueService.findAll();
        model
            .addAttribute("title", "Queue")
            .addAttribute("queues", queues);
        return "admin/queues";
    }

    @GetMapping(value = "/{id}")
    public  String getQueue(@PathVariable long id){
        orderQueueService.delete(orderQueueService.findOne(id));
        return "redirect:/admin/queues";
    }

    @GetMapping(value = "/new")
    public String addOrder(@RequestParam long orderId) {
        orderQueueService.createQueueItemByOrderId(orderId);
        return "redirect:/admin/queues";
    }
}
