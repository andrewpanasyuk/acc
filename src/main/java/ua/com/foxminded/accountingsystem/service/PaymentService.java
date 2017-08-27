package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Payment;
import ua.com.foxminded.accountingsystem.service.dto.CashInflowDto;

import java.util.List;

public interface PaymentService {

    List<CashInflowDto> findAllPayments();

    Payment create(Payment payment);

    Payment save(Payment payment);

    void delete(Payment payment);

}
