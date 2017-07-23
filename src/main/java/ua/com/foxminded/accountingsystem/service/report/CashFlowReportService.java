package ua.com.foxminded.accountingsystem.service.report;

import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.service.dto.CashInflowDto;
import ua.com.foxminded.accountingsystem.service.dto.CashOutflowDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CashFlowReportService {

    List<CashInflowDto> makeCashInflowReport(LocalDate beginDate, LocalDate endDate, Long serviceId);

    List<CashOutflowDto> makeCashOutflowReport(LocalDate beginDate, LocalDate endDate, Long serviceId);

    Map<Currency, Long> getTotalsCashInflowReport(List<CashInflowDto> listCashInflowDto);

    Map<Currency, Long> getTotalsCashOutflowReport(List<CashOutflowDto> listCashOutflowDto);

}
