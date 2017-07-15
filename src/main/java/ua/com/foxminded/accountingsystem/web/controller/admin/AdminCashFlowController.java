package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.accountingsystem.model.OrderQueue;
import ua.com.foxminded.accountingsystem.service.OrderQueueService;
import ua.com.foxminded.accountingsystem.service.OrderService;
import ua.com.foxminded.accountingsystem.service.dto.CashFlowDto;
import ua.com.foxminded.accountingsystem.service.report.CashFlowReportService;

import java.util.List;

@Controller
@RequestMapping("/admin/cashflow")
public class AdminCashFlowController {

    private CashFlowReportService cashFlowReportService;

    @Autowired
    public AdminCashFlowController(CashFlowReportService cashFlowReportService) {
        this.cashFlowReportService = cashFlowReportService;
    }

    @GetMapping
    public String getCashFlow(Model model) {
        List<CashFlowDto> cashInflowReport = cashFlowReportService.makeCashInflowReport();
        model
            .addAttribute("title", "Cash Flow Report")
            .addAttribute("cashInflowReport", cashInflowReport)
            .addAttribute("cashOutflowReport", cashInflowReport);
        return "admin/cashflow";
    }
}
