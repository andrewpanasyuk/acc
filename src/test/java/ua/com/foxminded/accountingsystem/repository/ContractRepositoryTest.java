package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.BeforeClass;
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
    private static Contract contract3;
    private static Contract postpay;
    private static Contract prepay;
    private static Contract trial;

    @BeforeClass
    public static void init() {

        Money contractEmployeeRate1 = new Money();
        contractEmployeeRate1.setCurrency(Currency.UAH);
        contractEmployeeRate1.setAmount(1500);

        Money contractEmployeeRate2 = new Money();
        contractEmployeeRate2.setCurrency(Currency.UAH);
        contractEmployeeRate2.setAmount(1500);

        Money price1 = new Money();
        price1.setCurrency(Currency.UAH);
        price1.setAmount(3000);

        Money price2 = new Money();
        price2.setCurrency(Currency.UAH);
        price2.setAmount(3000);

        Consultancy consultancy1 = new Consultancy();
        consultancy1.setId(50L);
        consultancy1.setName("java test");
        consultancy1.setDescription("bla-bla-bla");
        consultancy1.setCreatedBy("system");
        consultancy1.setCreatedDate(LocalDateTime.now());

        Consultancy consultancy2 = new Consultancy();
        consultancy2.setId(51L);
        consultancy2.setName("javascript test");
        consultancy2.setDescription("something written here");
        consultancy2.setCreatedBy("system");
        consultancy2.setCreatedDate(LocalDateTime.now());

        Deal deal1 = new Deal();
        deal1.setId(50L);
        deal1.setConsultancy(consultancy1);
        deal1.setCreatedBy("system");
        deal1.setCreatedDate(LocalDateTime.now());

        Deal deal2 = new Deal();
        deal2.setId(51L);
        deal2.setConsultancy(consultancy2);
        deal2.setCreatedBy("system");
        deal2.setCreatedDate(LocalDateTime.now());

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
        contract1.setDeal(deal1);
        contract1.setEmployee(employee1);
        contract1.setEmployeeRate(contractEmployeeRate1);
        contract1.setPrice(price1);
        contract1.setCreatedBy("system");
        contract1.setCreatedDate(LocalDateTime.now());

        contract2 = new Contract();
        contract2.setId(51L);
        contract2.setContractDate(LocalDate.of(2017, 06, 10));
        contract2.setPaymentType(PaymentType.POSTPAY);
        contract2.setDeal(deal2);
        contract2.setEmployee(employee2);
        contract2.setEmployeeRate(contractEmployeeRate2);
        contract2.setPrice(price2);
        contract2.setCreatedBy("system");
        contract2.setCreatedDate(LocalDateTime.now());

        contract3 = new Contract();
        contract3.setId(52L);
        contract3.setContractDate(LocalDate.of(2017, 06, 10));
        contract3.setPaymentType(PaymentType.PREPAY);
        contract3.setDeal(deal1);
        contract3.setEmployee(employee2);
        contract3.setEmployeeRate(contractEmployeeRate2);
        contract3.setPrice(price2);
        contract3.setCreatedBy("system");
        contract3.setCreatedDate(LocalDateTime.now());

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
    @ExpectedDataSet(value = "contracts/created-contract.xml", ignoreCols = {"id", "deal_id", "employee_id", "consultancy_id", "employee_rate_id", "price_id", "created_by", "created_date"})
    public void addContractTest() {
        repository.save(contract1);
    }

    @Test
    @DataSet(value = "contracts/stored-contracts.xml", cleanBefore = true)
    public void findAllContractsTest() {
        assertEquals(2, repository.findAll().size());
        assertThat(repository.findAll(), hasItems(contract1, contract2));
    }

    @Test
    @DataSet(value = "contracts/stored-contracts.xml", cleanBefore = true)
    public void findOneContractByIdTest() {
        assertEquals(contract1, repository.findOne(contract1.getId()));
    }

    @Test
    @DataSet(value = "contracts/stored-contracts.xml", cleanBefore = true)
    public void ifContractNotFoundByIdTest() {
        assertThat(repository.findOne(10500L), nullValue());
    }

    @Test
    @Commit
    @DataSet(value = "contracts/stored-contracts.xml", cleanBefore = true,  disableConstraints = true)
    @ExpectedDataSet(value = "contracts/expected-contracts.xml", ignoreCols = {"id", "deal_id", "employee_id", "consultancy_id", "employee_rate_id", "price_id", "created_by", "created_date"})
    public void deleteContractByIdTest() {
        repository.delete(contract2.getId());
    }

    @Test
    @DataSet(value = "contracts/find-contracts-for-payment.xml", cleanBefore = true, disableConstraints = true)
    public void findContractsForPaymentTest() {
        assertEquals(2, repository.findContractsForInvoicesCreation(24).size());
        assertThat(repository.findContractsForInvoicesCreation(24),
            hasItems(postpay, prepay));
    }

    @Test
    @DataSet(value = "contracts/contracts-for-one-deal.xml", cleanBefore = true)
    public void findPreviousContractByDealTest(){
        assertEquals(contract1, repository.findPreviousContractByDealId(contract3.getDeal().getId(), contract3.getContractDate()));
    }


}
