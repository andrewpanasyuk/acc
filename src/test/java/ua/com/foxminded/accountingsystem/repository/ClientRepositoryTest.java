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
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.model.OrderStatus;
import ua.com.foxminded.accountingsystem.model.PersonalAccount;

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
    private static Order johnOrder;
    private static Order janeOrder;
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

        johnEmail = new ClientFieldValue();
        johnEmail.setId(1L);
        johnEmail.setClientField(extraField);
        johnEmail.setValue("test@mail.com");
        janeEmail = new ClientFieldValue();
        janeEmail.setId(2L);
        janeEmail.setClientField(extraField);
        janeEmail.setValue("jane@mail.com");

        johnOrder = new Order();
        johnOrder.setId(1L);
        johnOrder.setStatus(OrderStatus.ACTIVE);
        janeOrder = new Order();
        janeOrder.setId(2L);
        janeOrder.setStatus(OrderStatus.ACTIVE);

        john.addOrder(johnOrder);
        jane.addOrder(janeOrder);
        john.addClientFieldValue(johnEmail);
        jane.addClientFieldValue(janeEmail);

        john.setId(1L);
        jane.setId(2L);
    }

    @Test
    @Commit
    @DataSet(value = "clients/empty.xml", cleanBefore = true, disableConstraints = true)
    @ExpectedDataSet(value = "clients/expected-clients.xml",
        ignoreCols = {"id", "client_id", "client_field_id", "personal_account_id"})
    public void addClient(){
        clientFieldRepository.save(extraField);
        repository.save(john);
    }

    @Test
    @DataSet(value = "clients/stored-client.xml")
    public void allClientsAreFound(){
        assertThat(repository.findAll(), hasItems(john, jane));
    }

    @Test
    @DataSet(value = "clients/stored-client.xml")
    public void clientsCanBeFoundById() {
        assertEquals(repository.findOne(1L), john);
        assertThat(repository.findOne(1L).getExtraFields(), contains(johnEmail));
        assertThat(repository.findOne(1L).getOrders(), contains(johnOrder));
        assertThat(repository.findOne(1L).getPersonalAccount(), equalTo(johnAccount));
        assertEquals(repository.getOne(1L), john);
        assertThat(repository.exists(1L), is(true));
    }

    @Test
    @DataSet(value = "clients/stored-client.xml")
    public void ifNoClientFoundReturnNull() {
        assertThat(repository.findOne(236L), nullValue());
    }

    @Test
    @DataSet("clients/empty.xml")
    public void ifNoUsersFoundReturnEmptyList() {
        assertThat(repository.findAll(), Matchers.is(empty()));
    }

    @Test
    @Commit
    @DataSet(value = "clients/stored-client.xml")
    @ExpectedDataSet("clients/expected-clients.xml")
    public void shouldDeleteByEntity() {
        repository.delete(jane);
    }

    @Test
    @Commit
    @DataSet(value = "clients/stored-client.xml")
    @ExpectedDataSet("clients/expected-clients.xml")
    public void shouldDeleteById() {
        repository.delete(jane.getId());
    }

}
