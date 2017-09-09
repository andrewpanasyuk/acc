package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> findPayments();

}
