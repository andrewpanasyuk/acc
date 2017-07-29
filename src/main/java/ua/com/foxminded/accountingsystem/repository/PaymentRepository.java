package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Payment;
import ua.com.foxminded.accountingsystem.service.dto.CashInflowDto;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select new ua.com.foxminded.accountingsystem.service.dto.CashInflowDto"
        + "(p.datePaid, s.name, p.id, p.sum) "
        + "from Payment p inner join p.invoice.contract.order.service s "
        + "where p.datePaid >= ?1 and p.datePaid <= ?2 "
        + "order by s.name, p.datePaid")
    List<CashInflowDto> findAllCashInflowByDatePaidBetween(LocalDate beginDate, LocalDate endDate);

    @Query("select new ua.com.foxminded.accountingsystem.service.dto.CashInflowDto"
        + "(p.datePaid, s.name, p.id, p.sum) "
        + "from Payment p inner join p.invoice.contract.order.service s "
        + "where p.datePaid >= ?1 and p.datePaid <= ?2 "
        + "and p.invoice.contract.order.service.id = ?3 "
        + "order by s.name, p.datePaid")
    List<CashInflowDto> findCashInflowByServiceAndDatePaidBetween(LocalDate beginDate, LocalDate endDate, Long serviceId);

}
