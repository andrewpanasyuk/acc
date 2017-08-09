package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.service.ClientService;
import ua.com.foxminded.accountingsystem.service.PersonalAccountMoneyTransferService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AdminClientControllerTest {

    @Mock
    private ClientService clientService;

    @Mock
    private PersonalAccountMoneyTransferService accountMoneyTransferHistoryService;

    private AdminClientController adminClientController;

    private MockMvc mockMvc;

    private static Client john;
    private static Client jane;
    private static List<Client> clients;

    @BeforeClass
    public static void initClass(){
        clients = new ArrayList<>();
        john = new Client();
        john.setFirstName("John");
        john.setLastName("Snow");
        john.setExtraFields(new ArrayList<>());
        john.setOrders(new ArrayList<>());
        john.setId(1L);
        jane = new Client();
        jane.setFirstName("Jane");
        jane.setLastName("White");
        jane.setExtraFields(new ArrayList<>());
        jane.setId(2L);
        clients.add(jane);
        clients.add(john);
    }

    @Before
    public void init(){
        adminClientController = new AdminClientController(clientService, accountMoneyTransferHistoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(adminClientController).build();
        when(clientService.findAll()).thenReturn(clients);
        when(clientService.findOne(john.getId())).thenReturn(john);
    }

    @Test
    public void clientsAddedToModel(){
        Model model = new ExtendedModelMap();
        assertThat(adminClientController.getAllClients(model), equalTo("admin/clients"));
        assertThat(model.asMap(), hasEntry("clients", clients));
    }

    @Test
    public void oneClientAddedToModel(){
        Model model = new ExtendedModelMap();
        assertThat(adminClientController.getClientByID(1L, model), equalTo("admin/client"));
        assertThat(model.asMap(), hasEntry("client", john));
    }

}
