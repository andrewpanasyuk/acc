package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.service.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    List<PaymentDto> findPayments();

}
