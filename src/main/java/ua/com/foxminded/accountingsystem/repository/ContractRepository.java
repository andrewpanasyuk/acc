package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Order;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("SELECT c FROM Contract as c WHERE c.closeType is NULL " +
        "AND function('DAY', c.paymentDate) = ?1 " +
        "AND c.id NOT IN(" +
        "SELECT processed FROM Contract as processed join processed.invoices as i " +
        "WHERE i.creationDate = ?2 )")
    List<Contract> findContractsForInvoicesCreation(int payDay, LocalDate today);

    List<Contract> findAllByOrderOrderByContractDateDesc(Order order);

    Contract findContractByOrderIdAndCloseTypeIsNull(Long orderId);

    Boolean existsContractByOrderId(Long id);
}
