package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.foxminded.accountingsystem.config.FmConstants;
import ua.com.foxminded.accountingsystem.model.Salary;
import ua.com.foxminded.accountingsystem.model.SalaryItem;
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

    @GetMapping
    public String getAllSalary(Model model) {
        model.addAttribute("allSalary", salaryService.findAllSalary());
        model.addAttribute("sumAllSalaryTotalAmount", salaryService.sumAllSalaryTotalAmount());
        return "admin/salary";
    }

    @GetMapping(value = "/{id}")
    public String getSalaryItemsBySalaryId(@PathVariable long id, Model model) {
        List<SalaryItem> salaryItems = salaryService.findSalaryItemsBySalaryId(id);
        model
            .addAttribute("salaryItems", salaryItems);
        return "admin/salaryItems";
    }

    @GetMapping("/payroll")
    public String getPayrollPeriodSelection(Model model) {
        model.addAttribute("dateFrom", LocalDate.now().minusMonths(1).plusDays(1));
        model.addAttribute("dateTo", LocalDate.now());
        return "admin/payroll";
    }

    @GetMapping(value = "/payroll", params = {"dateFrom", "dateTo"})
    public String getPayrollForPeriod(Model model,
                                      @RequestParam @DateTimeFormat(pattern = FmConstants.DATE_FORMAT) LocalDate dateFrom,
                                      @RequestParam @DateTimeFormat(pattern = FmConstants.DATE_FORMAT) LocalDate dateTo) {
        List<Salary> salaries = salaryService.prepareSalaries(dateFrom, dateTo);
        model.addAttribute("salaries", salaries);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        return "admin/payroll";
    }

    @PostMapping("/payroll")
    public String createSalary(@ModelAttribute Salary salary, RedirectAttributes redirectAttributes) {
        salaryService.create(salary);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FmConstants.DATE_FORMAT);
        redirectAttributes.addAttribute("dateFrom", salary.getDateFrom().format(formatter));
        redirectAttributes.addAttribute("dateTo", salary.getDateTo().plusDays(1).format(formatter));
        return "redirect:/admin/salary/payroll";
    }

}
