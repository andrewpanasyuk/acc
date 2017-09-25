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
@RequestMapping("/admin/conf/extra-fields")
public class AdminExtraFieldController {

    private final ClientFieldService clientFieldService;
    private final EmployeeFieldService employeeFieldService;
    private final String EXTRA_FIELDS = "/admin/conf/extra-fields";

    @Autowired
    public AdminExtraFieldController(ClientFieldService clientFieldService, EmployeeFieldService employeeFieldService){
        this.clientFieldService = clientFieldService;
        this.employeeFieldService = employeeFieldService;
    }

    @GetMapping
    public String getAllFields(Model model) {
        model.addAttribute("clientFields", clientFieldService.findAll())
            .addAttribute("employeeFields", employeeFieldService.findAll());
        return EXTRA_FIELDS;
    }

    @PostMapping("/client")
    public String createClientField(@ModelAttribute ClientField clientField) {
        clientFieldService.create(clientField);
        return "redirect:" + EXTRA_FIELDS;
    }

    @PostMapping("/employee")
    public String createEmployeeField(@ModelAttribute EmployeeField employeeField) {
        employeeFieldService.create(employeeField);
        return "redirect:" + EXTRA_FIELDS;
    }

    @DeleteMapping("/client/{id}")
    public String removeClientField(@PathVariable long id) {
        clientFieldService.delete(id);
        return "redirect:" + EXTRA_FIELDS;
    }

    @DeleteMapping("/employee/{id}")
    public String removeEmployeeField(@PathVariable long id) {
        employeeFieldService.delete(id);
        return "redirect:" + EXTRA_FIELDS;
    }
}
