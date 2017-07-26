package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Andrew on 04.06.2017.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    long countClientByOrders_Status(OrderStatus status);

    long countClientByCreatedByAfter(LocalDateTime date);
}
