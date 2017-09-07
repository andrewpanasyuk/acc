package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealQueue;

import java.util.List;

public interface DealQueueRepository extends JpaRepository<DealQueue, Long> {

    DealQueue findByDeal(Deal deal);

    List<DealQueue> findAllByRemoved(Boolean removed);
}
