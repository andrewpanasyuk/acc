package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Consultancy;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealStatus;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long>{

    List<Deal> findDealsByStatus(DealStatus dealStatus);

    long countDealsByStatusAndConsultancy(DealStatus dealStatus, Consultancy consultancy);

}
