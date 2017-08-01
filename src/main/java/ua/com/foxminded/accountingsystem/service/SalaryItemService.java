package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.SalaryItem;

import java.util.List;

public interface SalaryItemService {

    void delete(SalaryItem salaryItem);

    SalaryItem save(SalaryItem salaryItem);

    SalaryItem findOne(Long id);

    List<SalaryItem> findAll();

    void createSalaryItem(Invoice invoice);

    void createPretermSalaryItem(Invoice invoice);
}


