package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderQueue;
import ua.com.foxminded.accountingsystem.model.OrderStatus;
import ua.com.foxminded.accountingsystem.model.Priority;
import ua.com.foxminded.accountingsystem.service.OrderQueueService;
import ua.com.foxminded.accountingsystem.service.OrderService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/queues")
public class AdminOrderQueueController {

    private OrderQueueService orderQueueService;
    private OrderService orderService;

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
        Order order = orderService.findOne(orderId);
        OrderQueue orderQueue = new OrderQueue();
        orderQueue.setQueuingDate(LocalDate.now());
        if (order.getStatus().equals(OrderStatus.NEW)){
            orderQueue.setPriority(Priority.NORMAL);
        } else {
            orderQueue.setPriority(Priority.HIGH);
        }
        order.setStatus(OrderStatus.WAITING);
        order.setQueuingDate(LocalDate.now());
        orderQueue.setOrder(order);
        orderQueueService.save(orderQueue);
        return "redirect:/admin/queues";
    }
}
