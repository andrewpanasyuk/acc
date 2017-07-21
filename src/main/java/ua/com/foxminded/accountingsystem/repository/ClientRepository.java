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
    @Query(value = "update client_field_value set client_id=c.id, client_field_id=?1 from client c", nativeQuery = true)
    void setEmptyClientFieldValueByClientFieldId(Long id);
}
