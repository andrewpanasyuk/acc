package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.service.dto.CashFlowDto;

import java.util.List;

public interface CashFlowReportService {

    List<CashFlowDto> makeCashFlowReport();
}
