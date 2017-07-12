package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderQueue;
import ua.com.foxminded.accountingsystem.model.Priority;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class OrderQueueRepositoryTest extends AbstractRepositoryTest<OrderQueueRepository> {

    private static OrderQueue orderQueue;
    private static OrderQueue orderQueue_1;

    @Before
    public void init() {
        Order order = new Order();
        order.setId(1L);

        Order order_1 = new Order();
        order_1.setId(2L);

        orderQueue = new OrderQueue();
        orderQueue.setId(1L);
        orderQueue.setOrder(order);
        orderQueue.setPriority(Priority.NORMAL);
        orderQueue.setQueuingDate(LocalDate.of(2010, 01, 24));

        orderQueue_1 = new OrderQueue();
        orderQueue_1.setId(2L);
        orderQueue_1.setOrder(order_1);
        orderQueue_1.setPriority(Priority.HIGH);
        orderQueue_1.setQueuingDate(LocalDate.of(2015, 10, 01));
    }

    @Test
    @Commit
    @DataSet(value = "queues/empty.xml", disableConstraints = true, cleanBefore = true)
    @ExpectedDataSet(value = "queues/expected-queues.xml", ignoreCols = {"created_by", "created_date"})
    public void addOrder() {
        repository.save(orderQueue);
    }

    @Test
    @DataSet(value = "queues/stored-queues.xml", disableConstraints = true)
    public void findAllOrderTest() {
        assertEquals(2, repository.findAll().size());
    }

    @Test
    @DataSet(value = "queues/stored-queues.xml", disableConstraints = true)
    public void findOrderByIdTest() {
        assertEquals(orderQueue_1, repository.findOne(2L));
    }

    @Test
    @Commit
    @DataSet(value = "queues/stored-queues.xml", disableConstraints = true)
    @ExpectedDataSet(value = "queues/expected-queues.xml")
    public void deleteOrderByIdTest() {
        repository.delete(2L);
    }
}
