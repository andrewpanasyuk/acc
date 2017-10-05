package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.PaymentType;

import java.time.LocalDate;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("SELECT c FROM Contract as c WHERE c.closeType is NULL " +
        "AND function('DAY', c.paymentDate) <= ?1 " +
        "AND c.paymentType is NOT ua.com.foxminded.accountingsystem.model.PaymentType.TRIAL " +
        "AND NOT EXISTS(SELECT contract FROM Contract contract JOIN contract.invoices as i " +
        "LEFT JOIN i.payment as p WHERE p.id IS NULL AND contract.id = c.id)")
    List<Contract> findContractsForInvoicesCreation(int payDay);

    @Query("SELECT c FROM Contract as c " +
        "WHERE c.deal = ?1 " +
        "AND c.paymentType is ua.com.foxminded.accountingsystem.model.PaymentType.TRIAL " +
        "AND c.closeType is NULL")
    Contract findTrialActiveContractByDeal(Deal deal);

    List<Contract> findAllByDealOrderByContractDateDesc(Deal deal);

    Contract findContractByDealAndCloseTypeIsNull(Deal deal);

    boolean existsContractByDeal(Deal deal);

    boolean existsContractByDealAndCloseTypeIsNull(Deal deal);

    List<Contract> findAllByPaymentType(PaymentType paymentType);

    List<Contract> findAllByDealAndContractDateLessThanOrderByContractDateDesc(Deal deal, LocalDate contractDate);
}
