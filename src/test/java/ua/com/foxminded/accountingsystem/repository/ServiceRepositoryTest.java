package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Service;

import static org.junit.Assert.assertEquals;

public class ServiceRepositoryTest extends AbstractRepositoryTest<ServiceRepository> {

    private static Service service;

    @BeforeClass
    public static void init(){
        service = new Service();
        service.setName("java test");
        service.getEmployeeRate().setCurrency(Currency.UAH);
        Money money = new Money();
        money.setCurrency(Currency.EUR);
        service.getPrices().add(money);
        service.setDescription("java description");
    }

    @Test
    @Commit
    @DataSet(value = "services/empty.xml")
    @ExpectedDataSet("services/expected-services.xml")
    public void addService() {
        repository.save(service);
    }

    @Test
    @Commit
    @DataSet(value = "services/stored-services.xml", cleanAfter = true)
    public void testFindAllServices() {
        assertEquals(2, repository.findAll().size());
    }

    @Test
    @Commit
    @DataSet(value = "services/stored-services.xml", cleanAfter = true)
    public void testFindOneServiceById() {
        assertEquals("java test", repository.findOne(1L).getName());
    }



//    @Test
//    public void testFindAll(){
//        assertEquals(2, repository.findAll().size());
//    }
//
//    @Test
//    public void testFindOne(){
//        assertEquals("java", repository.findOne(1L).getName());
//    }
//
//    @Test
//    public void testSave(){
//        repository.save(service);
//        assertEquals(3, repository.findAll().size());
//    }
}
