package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.ClientField;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

public class ClientFieldRepositoryTest extends AbstractRepositoryTest<ClientFieldRepository> {

    private static ClientField email;
    private static ClientField country;
    private static ClientField persistedEmail;
    private static ClientField persistedCountry;

    @BeforeClass
    public static void init(){
        LocalDateTime dateTime = LocalDateTime.now();
        email = new ClientField();
        email.setName("email");
        email.setCreatedBy("system");
        email.setCreatedDate(dateTime);
        country = new ClientField();
        country.setName("country");
        country.setCreatedBy("system");
        country.setCreatedDate(dateTime);
        persistedEmail = new ClientField();
        persistedEmail.setName("email");
        persistedEmail.setId(1L);
        persistedEmail.setCreatedBy("system");
        persistedEmail.setCreatedDate(dateTime);
        persistedCountry = new ClientField();
        persistedCountry.setName("country");
        persistedCountry.setId(2L);
        persistedCountry.setCreatedBy("system");
        persistedCountry.setCreatedDate(dateTime);
    }

    @Test
    @Commit
    @DataSet(value = "client-fields/empty.xml")
    @ExpectedDataSet("client-fields/expected-client-fields.xml")
    public void addClientField(){
        repository.save(email);
    }

    @Test
    @DataSet(value = "client-fields/stored-client-fields.xml")
    public void userCanBeFoundByDifferentMethods() {
        assertThat(repository.findAll(), hasItems(persistedEmail, persistedCountry));
        assertThat(repository.findOne(persistedEmail.getId()), equalTo(persistedEmail));
        assertThat(repository.getOne(persistedEmail.getId()), equalTo(persistedEmail));
        assertThat(repository.exists(persistedEmail.getId()), is(true));
    }

    @Test
    @DataSet(value = "client-fields/stored-client-fields.xml")
    public void ifNoUserFoundReturnNull() {
        assertThat(repository.findOne(345L), nullValue());
    }

    @Test
    @DataSet("client-fields/empty.xml")
    public void ifNoUsersFoundReturnEmptyList() {
        assertThat(repository.findAll(), is(empty()));
    }

    @Test
    @Commit
    @DataSet(value = "client-fields/stored-client-fields.xml")
    @ExpectedDataSet("client-fields/expected-client-fields.xml")
    public void shouldDeleteByEntity() {
        repository.delete(persistedCountry);
    }

    @Test
    @Commit
    @DataSet(value = "client-fields/stored-client-fields.xml")
    @ExpectedDataSet("client-fields/expected-client-fields.xml")
    public void shouldDeleteById() {
        repository.delete(persistedCountry.getId());
    }

}
