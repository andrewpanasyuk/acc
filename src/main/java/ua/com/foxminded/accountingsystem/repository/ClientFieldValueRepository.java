package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.ClientFieldValue;

public interface ClientFieldValueRepository extends JpaRepository<ClientFieldValue, Long> {

    void deleteByClientField_Id(Long id);
}
