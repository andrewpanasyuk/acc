package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.model.Service;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long>{

    List<Deal> findDealsByStatus(DealStatus dealStatus);

    long countDealsByStatusAndService(DealStatus dealStatus, Service service);

}
