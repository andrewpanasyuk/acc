package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.DealStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ClientRepository extends JpaRepository<Client, Long> {

    long countClientByDealsStatus(DealStatus status);

    long countClientByCreatedDateAfter(LocalDateTime date);

    long countClientByDealsStatusAndDealsCloseDateAfter(DealStatus status, LocalDate closeDate);

    @Query("select count(distinct o.client) from Deal o " +
        "where o.status = ua.com.foxminded.accountingsystem.model.DealStatus.FROZEN " +
        "and not exists " +
        "(select o2.client from Deal o2 " +
        "where o2.client = o.client " +
        "and o2.status = ua.com.foxminded.accountingsystem.model.DealStatus.ACTIVE)")
    long countFrozenClients();


}
