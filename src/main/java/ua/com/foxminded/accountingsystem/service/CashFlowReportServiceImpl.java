package ua.com.foxminded.accountingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.repository.PaymentRepository;
import ua.com.foxminded.accountingsystem.repository.SalaryRepository;
import ua.com.foxminded.accountingsystem.service.dto.CashInflowDto;
import ua.com.foxminded.accountingsystem.service.dto.CashOutflowDto;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CashFlowReportServiceImpl implements CashFlowReportService {

    private final PaymentRepository paymentRepository;
    private final SalaryRepository salaryRepository;

    @Autowired
    public CashFlowReportServiceImpl(PaymentRepository paymentRepository, SalaryRepository salaryRepository) {
        this.paymentRepository = paymentRepository;
        this.salaryRepository = salaryRepository;
    }

    @Override
    public List<CashInflowDto> makeCashInflowReport(LocalDate beginDate, LocalDate endDate, Long serviceId) {

        if (serviceId != 0) {
            return paymentRepository.findCashInflowByServiceAndDatePaidBetween(beginDate, endDate, serviceId);
        } else {
            return paymentRepository.findAllCashInflowByDatePaidBetween(beginDate, endDate);
        }
    }

    @Override
    public List<CashOutflowDto> makeCashOutflowReport(LocalDate beginDate, LocalDate endDate, Long serviceId) {

        if (serviceId != 0) {
            return salaryRepository.findCashOutflowByServiceAndSalaryDateBetween(beginDate, endDate, serviceId);
        } else {
            return salaryRepository.findAllCashOutflowBySalaryDateBetween(beginDate, endDate);
        }
    }

    @Override
    public Map<Currency, Long> getTotalsCashInflowReport(List<CashInflowDto> cashInflowReport) {

        if (cashInflowReport == null) {
            return null;
        } else {
            return (cashInflowReport
                .stream()
                .map(cashInflowDto -> cashInflowDto.getPaymentSum())
                .collect(Collectors.groupingBy(flowSum -> flowSum.getCurrency(), Collectors.summingLong(flowSum -> flowSum.getAmount()))));
        }
    }

    @Override
    public Map<Currency, Long> getTotalsCashOutflowReport(List<CashOutflowDto> cashOutflowReport) {

        if (cashOutflowReport == null) {
            return null;
        } else {
            return (cashOutflowReport
                .stream()
                .map(cashOutflowDto -> cashOutflowDto.getSalarySum())
                .collect(Collectors.groupingBy(flowSum -> flowSum.getCurrency(), Collectors.summingLong(flowSum -> flowSum.getAmount()))));
        }
    }

}
