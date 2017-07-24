package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.accountingsystem.model.OrderQueue;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.service.OrderQueueService;
import ua.com.foxminded.accountingsystem.service.OrderService;
import ua.com.foxminded.accountingsystem.service.ServiceService;

import java.util.List;

@Controller
@RequestMapping("/admin/queues")
public class AdminOrderQueueController {

    private final OrderQueueService orderQueueService;
    private final OrderService orderService;
    private final ServiceService serviceService;

    @Autowired
    public AdminOrderQueueController(OrderQueueService orderQueueService, OrderService orderService, ServiceService serviceService) {
        this.orderQueueService = orderQueueService;
        this.orderService = orderService;
        this.serviceService = serviceService;
    }

    @GetMapping
    public String getQueues(Model model) {
        List<OrderQueue> queues = orderQueueService.findAll();
        List<Service> services = serviceService.findAll();
        model
            .addAttribute("title", "Queue")
            .addAttribute("services", services)
            .addAttribute("queues", queues);
        return "admin/queues";
    }

    @DeleteMapping(value = "/{id}")
    public  String deleteQueue(@PathVariable long id){
        orderQueueService.delete(id);
        return "redirect:/admin/queues";
    }

    @GetMapping(value = "/new")
    public String addOrder(@RequestParam long orderId) {
        orderQueueService.createQueueByOrderId(orderId);
        return "redirect:/admin/queues";
    }
}
