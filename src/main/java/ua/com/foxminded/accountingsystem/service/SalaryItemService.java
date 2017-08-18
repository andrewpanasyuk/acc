package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.SalaryItem;

import java.time.LocalDate;
import java.util.List;

public interface SalaryItemService {

    void delete(SalaryItem salaryItem);

    SalaryItem save(SalaryItem salaryItem);

    SalaryItem findOne(Long id);

    List<SalaryItem> findAll();

    SalaryItem createPretermSalaryItem(Invoice invoice, LocalDate closureDate);
}


