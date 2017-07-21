package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Client;

/**
 * Created by Andrew on 04.06.2017.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Modifying
    @Query(value = "insert into client_field_value (id, client_id, client_field_id, created_by, created_date)\n" +
        "select nextval('client_field_value_sequence'), c.id, ?1, 'system', now() from client c", nativeQuery = true)
    void insertEmptyClientFieldValueByClientFieldId(Long id);
}
