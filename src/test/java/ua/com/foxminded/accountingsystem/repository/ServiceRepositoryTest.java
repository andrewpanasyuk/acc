package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Service;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;

public class ServiceRepositoryTest extends AbstractRepositoryTest<ServiceRepository> {

    private static Service service;

    @BeforeClass
    public static void init(){
        service = new Service();
        service.setName("java test");
        service.getEmployeeRate().setCurrency(Currency.UAH);
        service.setCreatedBy("system");
        service.setCreatedDate(LocalDateTime.now());

        Money money = new Money();
        money.setCurrency(Currency.UAH);

        service.getPrices().add(money);
        service.setDescription("java description");
    }

    @Test
    @Commit
    @DataSet(value = "services/empty.xml", cleanBefore = true)
    @ExpectedDataSet(value = "services/expected-services.xml", ignoreCols = {"created_by", "created_date"})
    public void addService() {
        repository.save(service);
    }

    @Test
    @DataSet(value = "services/stored-services.xml")
    public void testFindAllServices() {
        assertEquals(2, repository.findAll().size());
    }

    @Test
    @DataSet(value = "services/stored-services.xml")
    public void testFindOneServiceById() {
        assertEquals("java test", repository.findOne(1L).getName());
    }

    @Test
    @DataSet(value = "services/stored-services.xml")
    public void ifServiceNotFoundByIdReturnNull() {
        assertThat(repository.findOne(10L), nullValue());
    }

    @Test
    @Commit
    @DataSet(value = "services/stored-services.xml", cleanBefore = true)
    @ExpectedDataSet("services/expected-services.xml")
    public void deleteServiceById() {
        repository.delete(2L);
    }

}
