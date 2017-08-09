package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.ClientField;
import ua.com.foxminded.accountingsystem.model.ClientFieldValue;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.model.PersonalAccount;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ClientRepositoryTest extends AbstractRepositoryTest<ClientRepository>{

    private static Client john;
    private static Client jane;
    private static ClientField extraField;
    private static ClientFieldValue johnEmail;
    private static ClientFieldValue janeEmail;
    private static Deal johnDeal;
    private static Deal janeDeal;
    private static PersonalAccount johnAccount;
    private static PersonalAccount janeAccount;

    @Autowired
    private ClientFieldRepository clientFieldRepository;

    @BeforeClass
    public static void init(){
        johnAccount = new PersonalAccount();
        johnAccount.setId(1L);
        janeAccount = new PersonalAccount();
        janeAccount.setId(2L);

        john = new Client("John", "Snow", new ArrayList<>(), new ArrayList<>(), johnAccount);
        jane = new Client("Jane", "White", new ArrayList<>(), new ArrayList<>(), janeAccount);

        extraField = new ClientField();
        extraField.setName("email");
        extraField.setCreatedBy("system");
        extraField.setCreatedDate(LocalDateTime.now());

        johnEmail = new ClientFieldValue();
        johnEmail.setId(1L);
        johnEmail.setClientField(extraField);
        johnEmail.setValue("test@mail.com");
        johnEmail.setCreatedBy("system");
        johnEmail.setCreatedDate(LocalDateTime.now());
        janeEmail = new ClientFieldValue();
        janeEmail.setId(2L);
        janeEmail.setClientField(extraField);
        janeEmail.setValue("jane@mail.com");
        janeEmail.setCreatedBy("system");
        janeEmail.setCreatedDate(LocalDateTime.now());

        johnDeal = new Deal();
        johnDeal.setId(1L);
        johnDeal.setStatus(DealStatus.ACTIVE);
        johnDeal.setCreatedBy("system");
        johnDeal.setCreatedDate(LocalDateTime.now());
        janeDeal = new Deal();
        janeDeal.setId(2L);
        janeDeal.setStatus(DealStatus.ACTIVE);
        johnDeal.setCreatedBy("system");
        johnDeal.setCreatedDate(LocalDateTime.now());

        john.addDeal(johnDeal);
        jane.addDeal(janeDeal);
        john.addClientFieldValue(johnEmail);
        jane.addClientFieldValue(janeEmail);

        john.setId(1L);
        jane.setId(2L);

        john.setCreatedBy("system");
        jane.setCreatedBy("system");
        john.setCreatedDate(LocalDateTime.now());
        jane.setCreatedDate(LocalDateTime.now());
    }

    @Test
    @Commit
    @DataSet(value = "clients/empty.xml", cleanBefore = true, disableConstraints = true)
    @ExpectedDataSet(value = "clients/expected-clients.xml",
        ignoreCols = {"id", "client_id", "client_field_id", "personal_account_id", "created_by", "created_date"})
    public void addClient(){
        clientFieldRepository.save(extraField);
        repository.save(john);
    }

    @Test
    @DataSet(value = "clients/stored-client.xml", cleanBefore = true)
    public void allClientsAreFound(){
        assertThat(repository.findAll(), hasItems(john, jane));
    }

    @Test
    @DataSet(value = "clients/stored-client.xml", cleanBefore = true)
    public void clientsCanBeFoundById() {
        assertEquals(repository.findOne(1L), john);
        assertThat(repository.findOne(1L).getExtraFields(), contains(johnEmail));
        assertThat(repository.findOne(1L).getDeals(), contains(johnDeal));
        assertThat(repository.findOne(1L).getPersonalAccount(), equalTo(johnAccount));
        assertEquals(repository.getOne(1L), john);
        assertThat(repository.exists(1L), is(true));
    }

    @Test
    @DataSet(value = "clients/stored-client.xml", cleanBefore = true)
    public void ifNoClientFoundReturnNull() {
        assertThat(repository.findOne(236L), nullValue());
    }

    @Test
    @DataSet(value = "clients/empty.xml", cleanBefore = true)
    public void ifNoUsersFoundReturnEmptyList() {
        assertThat(repository.findAll(), Matchers.is(empty()));
    }

    @Test
    @Commit
    @DataSet(value = "clients/stored-client.xml", cleanBefore = true)
    @ExpectedDataSet("clients/expected-clients.xml")
    public void shouldDeleteByEntity() {
        repository.delete(jane);
    }

    @Test
    @Commit
    @DataSet(value = "clients/stored-client.xml", cleanBefore = true)
    @ExpectedDataSet("clients/expected-clients.xml")
    public void shouldDeleteById() {
        repository.delete(jane.getId());
    }

}
