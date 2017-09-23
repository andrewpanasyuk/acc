package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.accountingsystem.model.ClientField;
import ua.com.foxminded.accountingsystem.model.EmployeeField;
import ua.com.foxminded.accountingsystem.service.ClientFieldService;
import ua.com.foxminded.accountingsystem.service.EmployeeFieldService;

@Controller
@RequestMapping("/admin/configurations/extra-fields")
public class AdminExtraFieldController {

    private final ClientFieldService clientFieldService;
    private final EmployeeFieldService employeeFieldService;

    @Autowired
    public AdminExtraFieldController(ClientFieldService clientFieldService, EmployeeFieldService employeeFieldService){
        this.clientFieldService = clientFieldService;
        this.employeeFieldService = employeeFieldService;
    }

    @GetMapping
    public String getAllFields(Model model) {
        model.addAttribute("clientFields", clientFieldService.findAll())
            .addAttribute("employeeFields", employeeFieldService.findAll());
        return "admin/extraFields";
    }

    @PostMapping("/client")
    public String createClientField(@ModelAttribute ClientField clientField) {
        clientFieldService.create(clientField);
        return "redirect:/admin/configurations/extra-fields/";
    }

    @PostMapping("/employee")
    public String createEmployeeField(@ModelAttribute EmployeeField employeeField) {
        employeeFieldService.create(employeeField);
        return "redirect:/admin/configurations/extra-fields/";
    }

    @DeleteMapping("/client/{id}")
    public String removeClientField(@PathVariable long id) {
        clientFieldService.delete(id);
        return "redirect:/admin/configurations/extra-fields";
    }

    @DeleteMapping("/employee/{id}")
    public String removeEmployeeField(@PathVariable long id) {
        employeeFieldService.delete(id);
        return "redirect:/admin/configurations/extra-fields";
    }
}
