package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Consultancy;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;

public class ConsultancyRepositoryTest extends AbstractRepositoryTest<ConsultancyRepository> {

    private static Consultancy consultancy;

    @BeforeClass
    public static void init(){
        consultancy = new Consultancy();
        consultancy.setName("java test");
        consultancy.getEmployeeRate().setCurrency(Currency.UAH);
        consultancy.setCreatedBy("system");
        consultancy.setCreatedDate(LocalDateTime.now());

        Money money = new Money();
        money.setCurrency(Currency.UAH);

        consultancy.getPrices().add(money);
        consultancy.setDescription("java description");
    }

    @Test
    @Commit
    @DataSet(value = "consultancies/empty.xml", cleanBefore = true)
    @ExpectedDataSet(value = "consultancies/expected-consultancies.xml", ignoreCols = {"created_by", "created_date"})
    public void addConsultancy() {
        repository.save(consultancy);
    }

    @Test
    @DataSet(value = "consultancies/stored-consultancies.xml")
    public void testFindAllConsultancies() {
        assertEquals(2, repository.findAll().size());
    }

    @Test
    @DataSet(value = "consultancies/stored-consultancies.xml")
    public void testFindOneConsultanciesById() {
        assertEquals("java test", repository.findOne(1L).getName());
    }

    @Test
    @DataSet(value = "consultancies/stored-consultancies.xml")
    public void ifConsultancyNotFoundByIdReturnNull() {
        assertThat(repository.findOne(10L), nullValue());
    }

    @Test
    @Commit
    @DataSet(value = "consultancies/stored-consultancies.xml", cleanBefore = true)
    @ExpectedDataSet("consultancies/expected-consultancies.xml")
    public void deleteConsultancyById() {
        repository.delete(2L);
    }

}
