package ua.com.foxminded.accountingsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Consultancy;

public interface ConsultancyRepository extends JpaRepository<Consultancy, Long> {
}
