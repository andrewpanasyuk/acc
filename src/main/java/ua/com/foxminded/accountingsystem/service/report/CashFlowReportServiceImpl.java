package ua.com.foxminded.accountingsystem.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public List<CashFlowDto> makeCashInflowReport() {

        return paymentRepository.findAllPaymentsByDatePaidBetween(LocalDate.of(2016,7,30),
            LocalDate.of(2017,7,30));
    }

    @Override
    public List<CashFlowDto> makeCashOutflowReport() {

        List<CashFlowDto> cashFlowReport = new ArrayList<>();


        return cashFlowReport;
    }

}
