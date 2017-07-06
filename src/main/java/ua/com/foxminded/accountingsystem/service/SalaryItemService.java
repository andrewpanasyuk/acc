package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.SalaryItem;

import java.time.LocalDate;

public interface SalaryItemService {

    SalaryItem createPostpaySalaryItem(Invoice invoice);

    SalaryItem createPostpayPretermSalaryItem(Invoice invoice, LocalDate closureDate);
}


