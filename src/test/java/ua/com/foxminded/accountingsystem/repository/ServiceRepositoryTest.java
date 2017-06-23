package ua.com.foxminded.accountingsystem.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Service;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ServiceRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private ServiceRepository repository;

    Service service;

    @Before
    public void init(){
        service = new Service();
        service.setName("test");
        service.getEmployeeRate().setCurrency(Currency.EUR);
        Money money = new Money();
        money.setCurrency(Currency.EUR);
        service.getPrices().add(money);
        service.setDescription("test description");
    }

    @Test
    public void testFindAll(){
        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void testFindOne(){
        assertEquals("java", repository.findOne(1L).getName());
    }

    @Test
    public void testSave(){
        repository.save(service);
        assertEquals(3, repository.findAll().size());
    }
}
