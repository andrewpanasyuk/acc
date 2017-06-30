package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.OrderQueue;

public interface OrderQueueRepository extends JpaRepository<OrderQueue, Long> {

    @Query("SELECT queue FROM OrderQueue queue WHERE order_id = ?1")
    OrderQueue findQueueByOrderId(Long id);
}
