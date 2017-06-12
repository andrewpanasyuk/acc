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
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminEmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public AdminEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(value = "employees")
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        return "admin/employees";

    }

    @GetMapping(value = "employee/{id}")
    public String getEmployee(@PathVariable long id, Model model) {
        Employee employee = employeeService.findOne(id);
        model.addAttribute("employee", employee);
        model.addAttribute("newObj",false);
        return "/admin/employee";
    }

    @PutMapping(value = "employee/{id}")
    public String updateEmployee(@PathVariable long id, @ModelAttribute Employee employee, Model model) {
        model.addAttribute("employee", employeeService.findOne(id));
        model.addAttribute("newObj",true);
        return "/admin/employee";
    }

    @DeleteMapping(value = "employee/{id}")
    public String removeEmployee(@PathVariable long id) {
        Employee employee = employeeService.findOne(id);
        employeeService.delete(employee);
        return "redirect:/admin/employees";
    }

    @PostMapping(value = "employee")
    public String save(@ModelAttribute Employee employee) {
        employeeService.save(employee);
        return "redirect:/admin/employees";
    }

    @GetMapping(value = "employee/createEmployee")
    public String createEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("newObj",true);
        return "admin/employee";
    }


}

