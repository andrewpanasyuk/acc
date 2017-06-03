package ua.com.foxminded.accountingsystem.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Price;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.service.ServiceService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/services")
public class ServiceController {

    private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public String getAllServices(Model model){
        List<Service> services = serviceService.getAllServices();
        log.debug("Found: "+services.size()+" services!");
        model.addAttribute("services", services);
        return "admin/services";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id){
        serviceService.remove(id);
        return "redirect:/admin/services";
    }

    @GetMapping("/{id}")
    public String getOneService(@PathVariable Long id, Model model) {
        model.addAttribute("service",serviceService.getById(id));
        model.addAttribute("currencies", Currency.values());
        return "admin/service";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Service service){
        serviceService.save(service);
        return "redirect:/admin/services";
    }

    @GetMapping("/newService")
    public String newService(Model model){
        Service service = new Service();
        List<Price> prices = new ArrayList<>();
        Price price1 = new Price();
        Price price2 = new Price();
        Price price3 = new Price();
        Price employeeRate = new Price();

        price1.setCurrency(Currency.UAH);
        price2.setCurrency(Currency.EUR);
        price3.setCurrency(Currency.USD);
        prices.add(price1);
        prices.add(price2);
        prices.add(price3);

        service.setPrices(prices);
        service.setEmployeeRate(employeeRate);
        model.addAttribute("service", service);
        model.addAttribute("currencies", Currency.values());
        return "admin/service";
    }

}
