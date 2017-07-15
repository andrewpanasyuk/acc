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

        List<Payment> payments =
            paymentRepository.findPaymentByDatePaidBetweenOrderByDatePaid(LocalDate.of(2016,7,30),
                LocalDate.of(2017,7,30));

        List<CashFlowDto> cashFlowReport = new ArrayList<>();

        for(Payment payment : payments) {
            cashFlowReport.add(new CashFlowDto(payment.getId(), DocumentType.PAYMENT, payment.getDatePaid(), payment.getSum(), null));
        }

        return cashFlowReport;
    }

    @Override
    public List<CashFlowDto> makeCashOutflowReport() {

        List<Payment> payments =
            paymentRepository.findPaymentByDatePaidBetweenOrderByDatePaid(LocalDate.of(2016,7,30),
                LocalDate.of(2017,7,30));

        List<Salary> salaries =
            salaryRepository.findSalaryBySalaryDateBetween(LocalDate.of(2016,7,30),
                LocalDate.of(2017,7,30));

        List<CashFlowDto> cashFlowReport = new ArrayList<>();

        for(Payment payment : payments) {
            cashFlowReport.add(new CashFlowDto(payment.getId(), DocumentType.PAYMENT, payment.getDatePaid(), payment.getSum(), null));
        }

        for(Salary salary : salaries) {
            cashFlowReport.add(new CashFlowDto(salary.getId(), DocumentType.SALARY, salary.getSalaryDate(), null, salary.getTotalAmount()));
        }

        cashFlowReport.sort(new Comparator<CashFlowDto>() {
            @Override
            public int compare(CashFlowDto o1, CashFlowDto o2) {
                return o1.getDocumentDate().compareTo(o2.getDocumentDate());
            }
        });


        return cashFlowReport;
    }

}
