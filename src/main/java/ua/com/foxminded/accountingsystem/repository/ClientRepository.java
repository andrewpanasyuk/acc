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

    @Query(value = "select count(*) from (select distinct o1.client_id " +
        "from orders o1 where o1.status='FROZEN' " +
        "except select distinct o2.client_id " +
        "from orders o2 where o2.status='ACTIVE') as client_count ", nativeQuery = true)
    long countFrozenClients();
}
