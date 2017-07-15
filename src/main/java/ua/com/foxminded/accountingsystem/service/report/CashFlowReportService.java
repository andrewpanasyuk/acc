package ua.com.foxminded.accountingsystem.service.report;

import ua.com.foxminded.accountingsystem.service.dto.CashFlowDto;

import java.util.List;

public interface CashFlowReportService {

    List<CashFlowDto> makeCashInflowReport();
    List<CashFlowDto> makeCashOutflowReport();
}
