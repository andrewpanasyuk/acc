package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.accountingsystem.service.ServiceService;
import ua.com.foxminded.accountingsystem.service.dto.CashInflowDto;
import ua.com.foxminded.accountingsystem.service.dto.CashOutflowDto;
import ua.com.foxminded.accountingsystem.service.report.CashFlowReportService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/cashflowreport")
public class AdminCashFlowReportController {

    private final CashFlowReportService cashFlowReportService;
    private final ServiceService service;

    @Autowired
    public AdminCashFlowReportController(CashFlowReportService cashFlowReportService, ServiceService service) {
        this.cashFlowReportService = cashFlowReportService;
        this.service = service;
    }

    @GetMapping(params = {"serviceId","beginDate", "endDate"})
    public String makeCashFlowReport(@RequestParam Long serviceId,
                                     @RequestParam String beginDate,
                                     @RequestParam String endDate,
                                     Model model) {

        List<CashInflowDto> cashInflowReport =
            cashFlowReportService.makeCashInflowReport(LocalDate.parse(beginDate), LocalDate.parse(endDate), serviceId);

        List<CashOutflowDto> cashOutflowReport =
            cashFlowReportService.makeCashOutflowReport(LocalDate.parse(beginDate), LocalDate.parse(endDate), serviceId);

        model
            .addAttribute("title", "Cash Flow Report")
            .addAttribute("services", service.findAll())
            .addAttribute("serviceId", serviceId)
            .addAttribute("serviceName", (serviceId != null) ? service.findOne(serviceId).getName() : "All services")
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
        model
            .addAttribute("title", "Cash Flow Report")
            .addAttribute("services", service.findAll())
            .addAttribute("serviceId", null)
            .addAttribute("serviceName", "All services")
            .addAttribute("beginDate", LocalDate.now().minusMonths(3))
            .addAttribute("endDate", LocalDate.now());
        return "admin/cashFlowReport";
    }
}
