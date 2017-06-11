package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.service.ServiceService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/services")
public class AdminServiceController {

    private static final Logger log = LoggerFactory.getLogger(AdminServiceController.class);

    private final ServiceService serviceService;

    @Autowired
    public AdminServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public String getAllServices(Model model) {
        List<Service> services = serviceService.findAll();
        log.debug("Found: " + services.size() + " services!");
        model.addAttribute("services", services);
        return "admin/services";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable long id) {
        log.debug("Remove service with id: " + id);
        if (id <= 0) {
            return "redirect:/admin/services";
        }
        serviceService.delete(id);
        return "redirect:/admin/services";
    }

    @GetMapping("/{id}")
    public String getOneService(@PathVariable long id, Model model) {
        model.addAttribute("service", serviceService.findOne(id));
        model.addAttribute("currencies", Currency.values());
        return "admin/service";
    }

    @PostMapping
    public String save(@ModelAttribute Service service) {
        log.debug("Save service: " + service.toString());
        serviceService.save(service);
        return "redirect:/admin/services";
    }

    @GetMapping("/new")
    public String newService(Model model) {
        List<Money> monies = new ArrayList<>();
        for (Currency currency : Arrays.asList(Currency.values())) {
            Money money = new Money();
            money.setCurrency(currency);
            monies.add(money);
        }

        Money employeeRate = new Money();
        employeeRate.setCurrency(Currency.UAH);

        Service service = new Service();
        service.setPrices(monies);
        service.setEmployeeRate(employeeRate);

        model.addAttribute("service", service);
        model.addAttribute("currencies", Currency.values());
        return "admin/service";
    }

}
