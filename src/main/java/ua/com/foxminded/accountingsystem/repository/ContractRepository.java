package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Deal;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("SELECT c FROM Contract as c WHERE c.closeType is NULL " +
        "AND function('DAY', c.paymentDate) <= ?1 " +
        "AND c.paymentType is NOT ua.com.foxminded.accountingsystem.model.PaymentType.TRIAL " +
        "AND c.id NOT IN(SELECT c FROM Contract c JOIN c.invoices as i " +
        "LEFT JOIN i.payment as p WHERE p.id IS NULL)")

    List<Contract> findContractsForInvoicesCreation(int payDay);

    List<Contract> findAllByDealOrderByContractDateDesc(Deal deal);

    Contract findContractByDealIdAndCloseTypeIsNull(Long dealId);

    boolean existsContractByDealId(Long id);
}
