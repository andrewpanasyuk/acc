package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Andrew on 04.06.2017.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    long countClientByOrdersStatus(OrderStatus status);

    long countClientByCreatedDateAfter(LocalDateTime date);

    long countClientByOrdersStatusAndOrdersCloseDateAfter(OrderStatus status, LocalDate closeDate);

    @Query("SELECT COUNT(DISTINCT c) FROM Client c " +
        "INNER JOIN c.orders o " +
        "INNER JOIN c.orders o2 " +
        "WHERE (o.status <> ua.com.foxminded.accountingsystem.model.OrderStatus.ACTIVE) " +
        "AND (o2.status = ua.com.foxminded.accountingsystem.model.OrderStatus.FROZEN) ")
    long countFrozenClients();
}
