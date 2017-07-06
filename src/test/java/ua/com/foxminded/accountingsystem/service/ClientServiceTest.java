package ua.com.foxminded.accountingsystem.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.ClientField;
import ua.com.foxminded.accountingsystem.model.ClientFieldValue;
import ua.com.foxminded.accountingsystem.repository.ClientRepository;
import ua.com.foxminded.accountingsystem.service.serviceJPA.ClientServiceJPA;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {
    private static Client client;
    @Mock
    private static ClientFieldService clientFieldService;
    @Mock
    private static ClientRepository clientRepository;
    @InjectMocks
    private static ClientServiceJPA clientService;
    private static List<ClientFieldValue> clientFieldValues;
    private static ArgumentCaptor<Client> argument;

    @BeforeClass
    public static void init(){
        clientFieldService = mock(ClientFieldService.class);
        clientRepository = mock(ClientRepository.class);
        client = new Client();
        client.setFirstName("John");
        client.setLastName("Smith");
    }

    @Test
    public void allExtraFieldsAddedToNewClient(){
        argument = ArgumentCaptor.forClass(Client.class);
        ClientField email = new ClientField();
        email.setName("email");
        ClientFieldValue emailValue = new ClientFieldValue();
        emailValue.setClientField(email);
        emailValue.setClient(client);
        clientFieldValues = new ArrayList<>();
        clientFieldValues.add(emailValue);
        List<ClientField> clientFields = new ArrayList<>();
        clientFields.add(email);
        when(clientFieldService.findAll()).thenReturn(clientFields);

        clientService.create(client);
        verify(clientRepository).save(argument.capture());
        assertEquals(clientFieldValues, argument.getValue().getExtraFields());
    }

}
