package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.ClientField;
import ua.com.foxminded.accountingsystem.model.ClientFieldValue;
import ua.com.foxminded.accountingsystem.repository.ClientFieldRepository;
import ua.com.foxminded.accountingsystem.repository.ClientFieldValueRepository;
import ua.com.foxminded.accountingsystem.repository.ClientRepository;
import ua.com.foxminded.accountingsystem.service.ClientFieldService;

import java.util.List;

@Service
public class ClientFieldServiceJPA implements ClientFieldService {

    private final ClientFieldRepository clientFieldRepository;
    private final ClientRepository clientRepository;
    private final ClientFieldValueRepository clientFieldValueRepository;

    @Autowired
    public ClientFieldServiceJPA(ClientFieldRepository clientFieldRepository, ClientRepository clientRepository,
                                 ClientFieldValueRepository clientFieldValueRepository) {
        this.clientFieldRepository = clientFieldRepository;
        this.clientRepository = clientRepository;
        this.clientFieldValueRepository = clientFieldValueRepository;
    }

    @Override
    public List<ClientField> findAll() {
        return clientFieldRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        List<ClientFieldValue> clientFieldValues = clientFieldValueRepository.findByClientField_Id(id);
        clientFieldValues.forEach(clientFieldValue -> {
            Client client = clientFieldValue.getClient();
            client.removeClientFieldValue(clientFieldValue);
            clientRepository.save(client);
        });
        clientFieldRepository.delete(id);
    }

    @Override
    public ClientField create(ClientField clientField) {
        ClientField savedField = clientFieldRepository.save(clientField);
        List<Client> clients = clientRepository.findAll();
        clients.forEach(client -> {
            ClientFieldValue clientFieldValue = new ClientFieldValue();
            clientFieldValue.setClientField(savedField);
            client.addClientFieldValue(clientFieldValue);
            clientRepository.save(client);
        });
        return savedField;
    }

}
