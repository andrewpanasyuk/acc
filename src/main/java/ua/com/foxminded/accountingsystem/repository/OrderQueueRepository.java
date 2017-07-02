package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.OrderQueue;

public interface OrderQueueRepository extends JpaRepository<OrderQueue, Long> {
}
