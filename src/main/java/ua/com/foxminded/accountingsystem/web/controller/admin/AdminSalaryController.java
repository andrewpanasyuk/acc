package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.foxminded.accountingsystem.model.Salary;
import ua.com.foxminded.accountingsystem.service.SalaryService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/admin/salary")
public class AdminSalaryController {

    private final SalaryService salaryService;

    @Autowired
    public AdminSalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping("/payroll")
    public String dateRangeForm(Model model) {
        model.addAttribute("dateFrom", LocalDate.now().minusMonths(1).plusDays(1));
        model.addAttribute("dateTo", LocalDate.now());
        return "admin/payroll";
    }

    @GetMapping(value = "/payroll", params = {"dateFrom", "dateTo"})
    public String payrollForDateRange(@RequestParam String dateFrom, @RequestParam String dateTo, Model model) {
        List<Salary> salaries = salaryService.prepareSalaries(
            LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
        model.addAttribute("salaries", salaries);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        return "admin/payroll";
    }

    @PostMapping("/payroll")
    public String createSalary(@ModelAttribute Salary salary, RedirectAttributes redirectAttributes) {
        salaryService.create(salary);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        redirectAttributes.addAttribute("dateFrom", salary.getDateFrom().format(formatter));
        redirectAttributes.addAttribute("dateTo", salary.getDateTo().format(formatter));
        return "redirect:/admin/salary/payroll";
    }

}
