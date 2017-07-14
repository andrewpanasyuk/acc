package ua.com.foxminded.accountingsystem.service;


import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Order;

import java.util.List;

public interface ContractService {

    List<Contract> findAll();

    Contract findOne(Long id);

    void delete(Long id);

    Contract save(Contract contract);

    Contract prepareNewByOrderId(Long orderId);

    List<Invoice> prepareIssueInvoices();

    List<Contract> findAllByOrder(Order order);

}
