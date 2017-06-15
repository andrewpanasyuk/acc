package ua.com.foxminded.accountingsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
