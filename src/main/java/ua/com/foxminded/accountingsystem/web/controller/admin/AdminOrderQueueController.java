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

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/queues")
public class AdminOrderQueueController {

    private final OrderQueueService orderQueueService;

    @Autowired
    public AdminOrderQueueController(OrderQueueService orderQueueService) {
        this.orderQueueService = orderQueueService;
    }

    @GetMapping
    public String getQueues(Model model) {
        Map<Service, List<OrderQueue>> queuesByService = orderQueueService.findAllGroupByService();
        model
            .addAttribute("title", "Queue")
            .addAttribute("queuesByService", queuesByService);
        return "admin/queues";
    }

    @GetMapping(value = "/new")
    public String addOrder(@RequestParam long orderId) {
        orderQueueService.createQueueByOrderId(orderId);
        return "redirect:/admin/queues";
    }

    @DeleteMapping(value = "/{id}/{cause}")
    public String delete(@PathVariable long id, @PathVariable String cause) {
        orderQueueService.deleteWithoutContract(id, cause);
        return "redirect:/admin/queues";
    }
}
