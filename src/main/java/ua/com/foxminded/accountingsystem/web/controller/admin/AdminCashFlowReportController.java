package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.service.ServiceService;
import ua.com.foxminded.accountingsystem.service.dto.CashInflowDto;
import ua.com.foxminded.accountingsystem.service.dto.CashOutflowDto;
import ua.com.foxminded.accountingsystem.service.report.CashFlowReportService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/reports/cashflow")
public class AdminCashFlowReportController {

    private final CashFlowReportService cashFlowReportService;
    private final ServiceService service;

    @Autowired
    public AdminCashFlowReportController(CashFlowReportService cashFlowReportService, ServiceService service) {
        this.cashFlowReportService = cashFlowReportService;
        this.service = service;
    }

    @PostMapping
    public String makeCashFlowReport(@ModelAttribute Service selectedService,
                                     @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate beginDate,
                                     @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate,
                                     Model model) {

        List<CashInflowDto> cashInflowReport =
        cashFlowReportService.makeCashInflowReport(beginDate, endDate, selectedService.getId());

        List<CashOutflowDto> cashOutflowReport =
        cashFlowReportService.makeCashOutflowReport(beginDate, endDate, selectedService.getId());

        selectedService.setName((selectedService.getId() != 0) ? service.findOne(selectedService.getId()).getName() : "All services");

        model
            .addAttribute("title", "Cash Flow Report")
            .addAttribute("services", service.findAll())
            .addAttribute("selectedService", selectedService)
            .addAttribute("beginDate", beginDate)
            .addAttribute("endDate", endDate)
            .addAttribute("cashInflowReport", cashInflowReport)
            .addAttribute("cashOutflowReport", cashOutflowReport)
            .addAttribute("totalsCashInflow", cashFlowReportService.getTotalsCashInflowReport(cashInflowReport))
            .addAttribute("totalsCashOutflow", cashFlowReportService.getTotalsCashOutflowReport(cashOutflowReport));

        return "admin/cashFlowReport";
    }

    @GetMapping
    public String getCashFlowReportPage(Model model) {

        Service selectedService = new Service();
        selectedService.setId(0);
        selectedService.setName("All services");

        model
            .addAttribute("title", "Cash Flow Report")
            .addAttribute("services", service.findAll())
            .addAttribute("selectedService", selectedService)
            .addAttribute("beginDate", LocalDate.now().minusMonths(3))
            .addAttribute("endDate", LocalDate.now());
        return "admin/cashFlowReport";
    }
}
