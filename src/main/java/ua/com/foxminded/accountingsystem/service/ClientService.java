package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Client;

import java.util.List;

/**
 * Created by Andrew on 04.06.2017.
 */
public interface ClientService {

    void removeClient(Client client);

    Client updateClient(Client client);

    Client getClientById(Long id);

    List<Client> getAllClients();
}
