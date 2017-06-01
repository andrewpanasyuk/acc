package ua.com.foxminded.accountingsystem.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.foxminded.accountingsystem.service.ServiceService;


@Controller
@RequestMapping("/admin/services")
public class ServicesController {

    private static final Logger logger = LoggerFactory.getLogger(ServicesController.class);

    private final ServiceService serviceService;

    @Autowired
    public ServicesController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public String getAllServices(Model model){
        logger.debug("Get all services!");
        model.addAttribute("services", serviceService.getAllServices());
        return "admin/services";
    }

    @GetMapping("/{id}")
    public String remove(@PathVariable Long id, Model model){
        serviceService.remove(id);
        model.addAttribute("services", serviceService.getAllServices());
        return "admin/services";
    }

}
