package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Service;

/**
 * Created by Andrew on 04.06.2017.
 */
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
