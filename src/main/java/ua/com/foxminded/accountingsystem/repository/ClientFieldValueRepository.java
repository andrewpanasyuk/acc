package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.ClientFieldValue;

import java.util.List;

public interface ClientFieldValueRepository extends JpaRepository<ClientFieldValue, Long> {

    List<ClientFieldValue> findByClientField_Id(Long clientFieldId);
}
