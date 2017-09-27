package ua.com.foxminded.accountingsystem.service;


import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Payment;

import java.util.List;

public interface ContractService {

    void setCompleted(Contract contract, String cause);

    List<Contract> findAll();

    Contract findOne(Long id);

    void delete(Long id);

    Contract saveByUser(Contract contract);

    Contract prepareNewByDeal(Deal deal);

    Contract prepareNewPaidContractFromTrialByDeal(Deal deal);

    List<Invoice> prepareIssueInvoices();

    List<Contract> findAllByDeal(Deal deal);

    Contract findActiveContractByDeal(Deal deal);

    boolean existsContractByDeal(Deal deal);

    boolean existsActiveContractByDeal(Deal deal);

    List<Payment> findAllRelatedPayments(Contract contract);
}
