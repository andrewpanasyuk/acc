package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.accountingsystem.model.DealQueue;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.service.DealQueueService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/queues")
public class AdminDealQueueController {

    private final DealQueueService dealQueueService;

    @Autowired
    public AdminDealQueueController(DealQueueService dealQueueService) {
        this.dealQueueService = dealQueueService;
    }

    @GetMapping
    public String getQueues(Model model) {
        Map<Service, List<DealQueue>> queuesByService = dealQueueService.findAllGroupByService();
        model
            .addAttribute("title", "Queue")
            .addAttribute("queuesByService", queuesByService);
        return "admin/queues";
    }

    @GetMapping(value = "/new")
    public String addDeal(@RequestParam long dealId) {
        dealQueueService.createQueueByDealId(dealId);
        return "redirect:/admin/queues";
    }

    @DeleteMapping(value = "/{id}/{cause}")
    public String deleteQueue(@PathVariable long id, @PathVariable DealStatus cause) {
        System.out.println(cause);
        dealQueueService.deleteQueue(id, cause);
        return "redirect:/admin/queues";
    }
}
