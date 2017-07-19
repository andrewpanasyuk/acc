package ua.com.foxminded.accountingsystem.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.DocumentType;
import ua.com.foxminded.accountingsystem.model.Payment;
import ua.com.foxminded.accountingsystem.model.Salary;
import ua.com.foxminded.accountingsystem.repository.PaymentRepository;
import ua.com.foxminded.accountingsystem.repository.SalaryRepository;
import ua.com.foxminded.accountingsystem.service.dto.CashFlowDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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
    public List<CashFlowDto> makeCashInflowReport(LocalDate beginDate, LocalDate endDate, Long serviceId) {

        if (serviceId != null) {
            return paymentRepository.findServicePaymentsByDatePaidBetween(beginDate, endDate, serviceId);
        } else {
            return paymentRepository.findAllPaymentsByDatePaidBetween(beginDate, endDate);
        }
    }

    @Override
    public List<CashFlowDto> makeCashOutflowReport(LocalDate beginDate, LocalDate endDate, Long serviceId) {

        if (serviceId != null) {
            return salaryRepository.findServiceSalariesBySalaryDateBetween(beginDate, endDate, serviceId);
        } else {
            return salaryRepository.findAllSalariesBySalaryDateBetween(beginDate, endDate);
        }
    }

    @Override
    public Map<Currency, Long> getTotalsCashFlowReport(List<CashFlowDto> cashFlowReport) {

        if (cashFlowReport == null) {
            return null;
        } else {
            return (cashFlowReport
                .stream()
                .map(cashFlowDto -> cashFlowDto.getMoney())
                .collect(Collectors.groupingBy(money -> money.getCurrency(), Collectors.summingLong(money -> money.getAmount()))));
        }
    }

}
