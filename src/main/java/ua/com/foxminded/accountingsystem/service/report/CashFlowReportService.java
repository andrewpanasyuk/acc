package ua.com.foxminded.accountingsystem.service.report;

import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.service.dto.CashFlowDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CashFlowReportService {

    List<CashFlowDto> makeCashInflowReport(LocalDate beginDate, LocalDate endDate, Long serviceId);
    List<CashFlowDto> makeCashOutflowReport();
    Map<Currency, Long> getTotalsCashFlowReport(List<CashFlowDto> listCashFlowDto);
}
