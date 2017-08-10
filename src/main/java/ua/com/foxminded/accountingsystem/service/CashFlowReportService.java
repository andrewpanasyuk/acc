package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.service.dto.CashInflowDto;
import ua.com.foxminded.accountingsystem.service.dto.CashOutflowDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface CashFlowReportService {

    List<CashInflowDto> makeCashInflowReport(LocalDate beginDate, LocalDate endDate, Long consultancyId);

    List<CashOutflowDto> makeCashOutflowReport(LocalDate beginDate, LocalDate endDate, Long consultancyId);

    Set<Money> getTotalsCashInflowReport(List<CashInflowDto> listCashInflowDto);

    Set<Money> getTotalsCashOutflowReport(List<CashOutflowDto> listCashOutflowDto);

    Set<Money> getBalanceCashFlowReport(Set<Money> totalsCashInflowReport, Set<Money> totalsCashOutflowReport);

}
