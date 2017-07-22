package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.ClientFieldValue;

public interface ClientFieldValueRepository extends JpaRepository<ClientFieldValue, Long> {

    void deleteByClientField_Id(Long id);

    @Modifying
    @Query(value = "insert into client_field_value (id, client_id, client_field_id, created_by, created_date, " +
        "last_modified_by, last_modified_date) select nextval('client_field_value_sequence'), c.id, ?1, 'system', now(), " +
        "'system', now() from client c", nativeQuery = true)
    void insertForAllClientsByClientFieldId(Long id);
}
