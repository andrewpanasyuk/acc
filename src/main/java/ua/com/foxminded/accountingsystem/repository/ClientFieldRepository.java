package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.ClientField;

public interface ClientFieldRepository extends JpaRepository<ClientField, Long>{
}
