package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Client;

import java.util.List;

public interface ClientService {

    void removeClient(Client client);

    Client updateClient(Client client);

    Client getClientById(Long id);

    List<Client> getAllClients();
}
