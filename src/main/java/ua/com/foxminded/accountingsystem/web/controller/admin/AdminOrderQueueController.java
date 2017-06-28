package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.foxminded.accountingsystem.model.OrderQueue;
import ua.com.foxminded.accountingsystem.service.OrderQueueService;

import java.util.List;

@Controller
@RequestMapping("/admin/queue")
public class AdminOrderQueueController {

    private OrderQueueService orderQueueService;

    @Autowired
    public AdminOrderQueueController(OrderQueueService orderQueueService) {
        this.orderQueueService = orderQueueService;
    }

    @GetMapping
    public String getQueues(Model model) {
        List<OrderQueue> queues = orderQueueService.findAll();
        model
            .addAttribute("title", "Queue")
            .addAttribute("queues", queues);
        return "admin/queue";
    }
}
