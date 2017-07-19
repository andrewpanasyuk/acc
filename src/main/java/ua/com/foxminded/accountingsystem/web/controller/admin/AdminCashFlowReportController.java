package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.OrderQueue;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.service.OrderQueueService;
import ua.com.foxminded.accountingsystem.service.OrderService;
import ua.com.foxminded.accountingsystem.service.ServiceService;
import ua.com.foxminded.accountingsystem.service.dto.CashFlowDto;
import ua.com.foxminded.accountingsystem.service.report.CashFlowReportService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        List<CashFlowDto> cashInflowReport =
            cashFlowReportService.makeCashInflowReport(LocalDate.parse(beginDate), LocalDate.parse(endDate), serviceId);
        List<CashFlowDto> cashOutflowReport =
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
            .addAttribute("totalsCashInflow", cashFlowReportService.getTotalsCashFlowReport(cashInflowReport))
            .addAttribute("totalsCashOutflow", cashFlowReportService.getTotalsCashFlowReport(cashOutflowReport));

        return "admin/cashflowreport";
    }

    @GetMapping
    public String getCashFlowReportPage(Model model) {
        model
            .addAttribute("title", "Cash Flow Report")
            .addAttribute("services", service.findAll())
            .addAttribute("serviceId", null)
            .addAttribute("serviceName", "All services")
            .addAttribute("beginDate", LocalDate.now().minusMonths(1).plusDays(1))
            .addAttribute("endDate", LocalDate.now())
            .addAttribute("cashInflowReport", null)
            .addAttribute("cashOutflowReport", null);
        return "admin/cashflowreport";
    }
}
