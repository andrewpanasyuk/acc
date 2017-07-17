package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Payment;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.service.dto.CashFlowDto;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select new ua.com.foxminded.accountingsystem.service.dto.CashFlowDto"
        + "(p.datePaid, p.id, p.sum) "
        + "from Payment p "
        + "where p.datePaid >= ?1 and p.datePaid <= ?2 "
        + "order by p.datePaid")
    List<CashFlowDto> findAllPaymentsByDatePaidBetween(LocalDate beginDate, LocalDate endDate);

    @Query("select new ua.com.foxminded.accountingsystem.service.dto.CashFlowDto"
        + "(p.datePaid, p.id, p.sum) "
        + "from Payment p "
        + "where p.datePaid >= ?1 and p.datePaid <= ?2 "
        + "and p.invoice.contract.order.service.id = ?3 "
        + "order by p.datePaid")
    List<CashFlowDto> findServicePaymentsByDatePaidBetween(LocalDate beginDate, LocalDate endDate, Long serviceId);

//    List<Payment> findPaymentByDatePaidBetweenOrderByDatePaid(LocalDate beginDate, LocalDate endDate);

//    @Query("select new ua.com.foxminded.accountingsystem.service.dto.CashFlowDto"
//        + "(p.id, p.datePaid, p.sum) "
//        + "from Payment p "
//        + "where p.datePaid >= ?1 and p.datePaid <= ?2")
//
//    List<Payment> findPaymentByDatePaidBetweenAndInvoiceContractOrderService(LocalDate beginDate, LocalDate endDate, Service service);


//    List<Payment> findPaymentByDatePaidGreaterThanEqualAndDatePaidLessThanEqual(LocalDate beginDate, LocalDate endDate);
}
