package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.ClientField;
import ua.com.foxminded.accountingsystem.model.EmployeeField;
import ua.com.foxminded.accountingsystem.service.ClientFieldService;
import ua.com.foxminded.accountingsystem.service.EmployeeFieldService;

@Controller
@RequestMapping("/admin/fields")
public class AdminAdditionalFieldController {

    private final ClientFieldService clientFieldService;
    private final EmployeeFieldService employeeFieldService;

    @Autowired
    public AdminAdditionalFieldController(ClientFieldService clientFieldService, EmployeeFieldService employeeFieldService){
        this.clientFieldService = clientFieldService;
        this.employeeFieldService = employeeFieldService;
    }

    @GetMapping
    public String getAllFields(Model model) {
        model.addAttribute("clientFields", clientFieldService.findAll())
            .addAttribute("employeeFields", employeeFieldService.findAll());
        return "admin/additionalFields";
    }

    @PostMapping("/client")
    public String createClientField(@ModelAttribute ClientField clientField) {
        clientFieldService.create(clientField);
        return "redirect:/admin/fields/";
    }

    @PostMapping("/employee")
    public String createEmployeeField(@ModelAttribute EmployeeField employeeField) {
        employeeFieldService.create(employeeField);
        return "redirect:/admin/fields/";
    }

    @DeleteMapping("/client/{id}")
    public String removeClientField(@PathVariable long id) {
        clientFieldService.delete(id);
        return "redirect:/admin/fields";
    }

    @DeleteMapping("/employee/{id}")
    public String removeEmployeeField(@PathVariable long id) {
        employeeFieldService.delete(id);
        return "redirect:/admin/fields";
    }
}
