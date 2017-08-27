package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Payment;
import ua.com.foxminded.accountingsystem.repository.PaymentRepository;
import ua.com.foxminded.accountingsystem.service.PaymentService;
import ua.com.foxminded.accountingsystem.service.dto.CashInflowDto;

import java.util.List;

@Service
public class PaymentServiceJPA implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceJPA(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<CashInflowDto> findAllPayments() {
        return paymentRepository.findAllPayments();
    }

    @Override
    public Payment create(Payment payment) {
        return null;
    }

    @Override
    public Payment save(Payment payment) {
        return null;
    }

    @Override
    public void delete(Payment payment) {

    }
}
