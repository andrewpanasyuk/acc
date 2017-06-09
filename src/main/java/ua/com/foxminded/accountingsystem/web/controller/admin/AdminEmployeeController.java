package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.Price;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.service.EmployeeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/employees")
public class AdminEmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public AdminEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    @GetMapping
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "admin/employees";

    }

    @PutMapping(value = "/{id}")
    public String updateEmployee(@ModelAttribute Employee employee) {
        employeeService.save(employee);
        return "redirect:/admin/employees";
    }

    @DeleteMapping(value = "/{id}")
    public String removeEmployee(@PathVariable long id) {
        Employee employee = employeeService.findOne(id);
        employeeService.delete(employee);
        return "redirect:/admin/employees";
    }

    @PostMapping
    public String save(@ModelAttribute Employee employee){
        employeeService.save(employee);
        return "redirect:/admin/employess";
    }

    @GetMapping(value = "/createEmployee")
    public String createEmployee(Model model){
        model.addAttribute("employee", new Employee());
        return "admin/employee";
    }


}

