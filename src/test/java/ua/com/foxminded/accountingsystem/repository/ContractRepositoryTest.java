package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.*;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ContractRepositoryTest extends AbstractRepositoryTest<ContractRepository> {

    private static Contract contract1;
    private static Contract contract2;

    @BeforeClass
    public static void init() {

        Money contractEmployeeRate1 = new Money();
        contractEmployeeRate1.setCurrency(Currency.UAH);
        contractEmployeeRate1.setPrice(1500);

        Money contractEmployeeRate2 = new Money();
        contractEmployeeRate2.setCurrency(Currency.UAH);
        contractEmployeeRate2.setPrice(1500);

        Money price1 = new Money();
        price1.setCurrency(Currency.UAH);
        price1.setPrice(3000);

        Money price2 = new Money();
        price2.setCurrency(Currency.UAH);
        price2.setPrice(3000);

        Service service1 = new Service();
        service1.setId(50L);
        service1.setName("java test");
        service1.setDescription("bla-bla-bla");


        Service service2 = new Service();
        service2.setId(51L);
        service2.setName("javascript test");
        service2.setDescription("something written here");

        Order order1 = new Order();
        order1.setId(50L);
        order1.setService(service1);

        Order order2 = new Order();
        order2.setId(51L);
        order2.setService(service2);

        Employee employee1 = new Employee();
        employee1.setId(50L);

        Employee employee2 = new Employee();
        employee2.setId(51L);

        contract1 = new Contract();
        contract1.setId(50L);
        contract1.setContractDate(LocalDate.of(2017, 06, 01));
        contract1.setPaymentType(PaymentType.PREPAY);
        contract1.setOrder(order1);
        contract1.setEmployee(employee1);
        contract1.setEmployeeRate(contractEmployeeRate1);
        contract1.setPrice(price1);

        contract2 = new Contract();
        contract2.setId(51L);
        contract2.setContractDate(LocalDate.of(2017, 06, 10));
        contract2.setPaymentType(PaymentType.POSTPAY);
        contract2.setOrder(order2);
        contract2.setEmployee(employee2);
        contract2.setEmployeeRate(contractEmployeeRate2);
        contract2.setPrice(price2);
    }

    @Test
    @Commit
    @DataSet(value = "contracts/empty.xml", cleanBefore = true, disableConstraints = true)
    @ExpectedDataSet("contracts/expected-contracts.xml")
    public void addContractTest() {
        repository.save(contract1);
    }

    @Test
    @DataSet(value = "contracts/stored-contracts.xml")
    public void findAllContractsTest() {
        assertEquals(2, repository.findAll().size());
        assertThat(repository.findAll(), hasItems(contract1, contract2));
    }

    @Test
    @DataSet(value = "contracts/stored-contracts.xml")
    public void findOneContractByIdTest() {
        assertEquals(contract1, repository.findOne(50L));
    }

    @Test
    @DataSet(value = "contracts/stored-contracts.xml")
    public void ifContractNotFoundByIdTest() {
        assertThat(repository.findOne(10L), nullValue());
    }

    @Test
    @Commit
    @DataSet(value = "contracts/stored-contracts.xml", cleanBefore = true,  disableConstraints = true)
    @ExpectedDataSet("contracts/expected-contracts.xml")
    public void deleteContractByIdTest() {
        repository.delete(51L);
    }

}
