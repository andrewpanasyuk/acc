package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderStatus;
import ua.com.foxminded.accountingsystem.model.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class OrderRepositoryTest extends AbstractRepositoryTest<OrderRepository> {

    private static Order order;
    private static Order order_1;

    @Before
    public void init() {
        Service service = new Service();
        service.setId(1L);

        Service service_1 = new Service();
        service_1.setId(2L);

        Client client = new Client();
        client.setId(1L);

        Client client_1 = new Client();
        client_1.setId(2L);

        order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.ACTIVE);
        order.setService(service);
        order.setClient(client);
        order.setOpenDate(LocalDate.of(2017, 01, 24));
        order.setQueuingDate(LocalDate.of(2017, 01, 25));
        order.setCloseDate(LocalDate.of(2017, 01, 25));

        order_1 = new Order();
        order_1.setId(2L);
        order_1.setStatus(OrderStatus.WAITING);
        order_1.setService(service_1);
        order_1.setClient(client_1);
        order_1.setOpenDate(LocalDate.of(2010, 01, 24));
    }


    @Test
    @Commit
    @DataSet(value = "orders/empty.xml", disableConstraints = true, cleanBefore = true)
    @ExpectedDataSet("orders/expected-orders.xml")
    public void addOrder() {
        repository.save(order);
    }

    @Test
    @DataSet(value = "orders/stored-orders.xml", disableConstraints = true)
    public void findAllOrderTest() {
        assertEquals(2, repository.findAll().size());
    }

    @Test
    @DataSet(value = "orders/stored-orders.xml" , disableConstraints = true)
    public void findOrderByIdTest() {
        assertEquals(order_1, repository.findOne(2L));
    }

    @Test
    @Commit
    @DataSet(value = "orders/stored-orders.xml", disableConstraints = true)
    @ExpectedDataSet(value = "orders/expected-orders.xml")
    public void deleteOrderByIdTest() {
        repository.delete(2L);
    }
}
