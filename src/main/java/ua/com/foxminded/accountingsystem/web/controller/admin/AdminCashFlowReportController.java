package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Consultancy;
import ua.com.foxminded.accountingsystem.service.ConsultancyService;
import ua.com.foxminded.accountingsystem.service.dto.CashInflowDto;
import ua.com.foxminded.accountingsystem.service.dto.CashOutflowDto;
import ua.com.foxminded.accountingsystem.service.CashFlowReportService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/reports/cashflow")
public class AdminCashFlowReportController {

    private final CashFlowReportService cashFlowReportService;
    private final ConsultancyService consultancyService;

    @Autowired
    public AdminCashFlowReportController(CashFlowReportService cashFlowReportService, ConsultancyService consultancyService) {
        this.cashFlowReportService = cashFlowReportService;
        this.consultancyService = consultancyService;
    }

    @PostMapping
    public String makeCashFlowReport(@ModelAttribute Consultancy selectedConsultancy,
                                     @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate beginDate,
                                     @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate,
                                     Model model) {

        List<CashInflowDto> cashInflowReport =
        cashFlowReportService.makeCashInflowReport(beginDate, endDate, selectedConsultancy.getId());

        List<CashOutflowDto> cashOutflowReport =
        cashFlowReportService.makeCashOutflowReport(beginDate, endDate, selectedConsultancy.getId());

        selectedConsultancy.setName((selectedConsultancy.getId() != 0) ? consultancyService.findOne(selectedConsultancy.getId()).getName() : "All consultancies");

        Set<Money> totalsCashInflow = cashFlowReportService.getTotalsCashInflowReport(cashInflowReport);
        Set<Money> totalsCashOutflow = cashFlowReportService.getTotalsCashOutflowReport(cashOutflowReport);
        Set<Money> balanceCashFlowReport = cashFlowReportService.getBalanceCashFlowReport(totalsCashInflow, totalsCashOutflow);

        model
            .addAttribute("title", "Cash Flow Report")
            .addAttribute("consultancies", consultancyService.findAll())
            .addAttribute("selectedConsultancy", selectedConsultancy)
            .addAttribute("beginDate", beginDate)
            .addAttribute("endDate", endDate)
            .addAttribute("cashInflowReport", cashInflowReport)
            .addAttribute("cashOutflowReport", cashOutflowReport)
            .addAttribute("totalsCashInflow", totalsCashInflow)
            .addAttribute("totalsCashOutflow", totalsCashOutflow)
            .addAttribute("balanceCashFlowReport", balanceCashFlowReport);

        return "admin/cashFlowReport";
    }

    @GetMapping
    public String getCashFlowReportPage(Model model) {

        Consultancy selectedConsultancy = new Consultancy();
        selectedConsultancy.setId(0);
        selectedConsultancy.setName("All consultancies");

        model
            .addAttribute("title", "Cash Flow Report")
            .addAttribute("consultancies", consultancyService.findAll())
            .addAttribute("selectedConsultancy", selectedConsultancy)
            .addAttribute("beginDate", LocalDate.now().minusMonths(3))
            .addAttribute("endDate", LocalDate.now());
        return "admin/cashFlowReport";
    }
}
