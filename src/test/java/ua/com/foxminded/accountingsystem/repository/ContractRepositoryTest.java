package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.*;
import ua.com.foxminded.accountingsystem.model.Contract;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class ContractRepositoryTest extends AbstractRepositoryTest<ContractRepository> {

    private static Contract contract1;
    private static Contract contract2;
    private static Contract postpay;
    private static Contract prepay;
    private static Contract trial;

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
        service1.setCreatedBy("system");
        service1.setCreatedDate(LocalDateTime.now());


        Service service2 = new Service();
        service2.setId(51L);
        service2.setName("javascript test");
        service2.setDescription("something written here");
        service2.setCreatedBy("system");
        service2.setCreatedDate(LocalDateTime.now());

        Order order1 = new Order();
        order1.setId(50L);
        order1.setService(service1);
        order1.setCreatedBy("system");
        order1.setCreatedDate(LocalDateTime.now());

        Order order2 = new Order();
        order2.setId(51L);
        order2.setService(service2);
        order2.setCreatedBy("system");
        order2.setCreatedDate(LocalDateTime.now());

        Employee employee1 = new Employee();
        employee1.setId(50L);
        employee1.setCreatedBy("system");
        employee1.setCreatedDate(LocalDateTime.now());

        Employee employee2 = new Employee();
        employee2.setId(51L);
        employee2.setCreatedBy("system");
        employee2.setCreatedDate(LocalDateTime.now());

        contract1 = new Contract();
        contract1.setId(50L);
        contract1.setContractDate(LocalDate.of(2017, 06, 01));
        contract1.setPaymentType(PaymentType.PREPAY);
        contract1.setOrder(order1);
        contract1.setEmployee(employee1);
        contract1.setEmployeeRate(contractEmployeeRate1);
        contract1.setPrice(price1);
        contract1.setCreatedBy("system");
        contract1.setCreatedDate(LocalDateTime.now());

        contract2 = new Contract();
        contract2.setId(51L);
        contract2.setContractDate(LocalDate.of(2017, 06, 10));
        contract2.setPaymentType(PaymentType.POSTPAY);
        contract2.setOrder(order2);
        contract2.setEmployee(employee2);
        contract2.setEmployeeRate(contractEmployeeRate2);
        contract2.setPrice(price2);
        contract2.setCreatedBy("system");
        contract2.setCreatedDate(LocalDateTime.now());

        postpay = new Contract();
        prepay = new Contract();
        trial = new Contract();
        postpay.setPaymentDate(LocalDate.of(2017, 01, 24));
        postpay.setPaymentType(PaymentType.POSTPAY);
        postpay.setId(2L);
        postpay.setCreatedBy("system");
        postpay.setCreatedDate(LocalDateTime.now());
        prepay.setPaymentDate(LocalDate.of(2017, 07, 24));
        prepay.setPaymentType(PaymentType.PREPAY);
        prepay.setId(1L);
        prepay.setCreatedBy("system");
        prepay.setCreatedDate(LocalDateTime.now());
        trial.setPaymentDate(LocalDate.of(2017, 12, 24));
        trial.setPaymentType(PaymentType.TRIAL);
        trial.setId(3L);
        trial.setCreatedBy("system");
        trial.setCreatedDate(LocalDateTime.now());
    }

    @Test
    @Commit
    @DataSet(value = "contracts/empty.xml", cleanBefore = true, disableConstraints = true)
    @ExpectedDataSet(value = "contracts/created-contract.xml", ignoreCols = {"id", "order_id", "employee_id", "service_id", "employee_rate_id", "price_id", "created_by", "created_date"})
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
        assertEquals(contract1, repository.findOne(contract1.getId()));
    }

    @Test
    @DataSet(value = "contracts/stored-contracts.xml")
    public void ifContractNotFoundByIdTest() {
        assertThat(repository.findOne(10500L), nullValue());
    }

    @Test
    @Commit
    @DataSet(value = "contracts/stored-contracts.xml", cleanBefore = true,  disableConstraints = true)
    @ExpectedDataSet(value = "contracts/expected-contracts.xml", ignoreCols = {"id", "order_id", "employee_id", "service_id", "employee_rate_id", "price_id", "created_by", "created_date"})
    public void deleteContractByIdTest() {
        repository.delete(contract2.getId());
    }

    @Test
    @DataSet(value = "contracts/find-contracts-for-payment.xml", cleanBefore = true, disableConstraints = true)
    public void findContractsForPaymentTest() {
        assertEquals(3, repository.findContractsForInvoicesCreation(24,
            LocalDate.of(2017, 07, 21)).size());
        assertThat(repository.findContractsForInvoicesCreation(24,
            LocalDate.of(2017, 07, 21)),
            hasItems(postpay, prepay, trial));
    }

}
