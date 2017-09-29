package ua.com.foxminded.accountingsystem.service;


import ua.com.foxminded.accountingsystem.model.CloseType;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Payment;
import ua.com.foxminded.accountingsystem.model.PaymentType;
import java.util.List;

public interface ContractService {

    void close(Contract contract, CloseType closeType, String cause);

    List<Contract> findAll();

    Contract findOne(Long id);

    void delete(Long id);

    Contract save(Contract contract);

    Contract prepareNewContractByDealId(Long dealId);

    List<Invoice> prepareIssueInvoices();

    List<Contract> findAllByDeal(Deal deal);

    Contract findActiveContractByDeal(Deal deal);

    Contract findTrialActiveContractByDeal(Deal deal);

    boolean existsContractByDeal(Deal deal);

    boolean existsActiveContractByDeal(Deal deal);

    List<Payment> findAllRelatedPayments(Contract contract);

    List<Contract> findAllByPaymentType(PaymentType paymentType);
}
