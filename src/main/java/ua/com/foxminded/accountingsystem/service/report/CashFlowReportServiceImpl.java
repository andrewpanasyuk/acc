package ua.com.foxminded.accountingsystem.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.accountingsystem.repository.PaymentRepository;
import ua.com.foxminded.accountingsystem.repository.SalaryRepository;
import ua.com.foxminded.accountingsystem.service.CashFlowReportService;
import ua.com.foxminded.accountingsystem.service.dto.CashFlowDto;

import java.util.ArrayList;
import java.util.List;

public class CashFlowReportServiceImpl implements CashFlowReportService {

    private final PaymentRepository paymentRepository;
    private final SalaryRepository salaryRepository;

    @Autowired
    public CashFlowReportServiceImpl(PaymentRepository paymentRepository, SalaryRepository salaryRepository) {
        this.paymentRepository = paymentRepository;
        this.salaryRepository = salaryRepository;
    }

    @Override
    public List<CashFlowDto> makeCashFlowReport() {

        return new ArrayList<>();
    }
}
