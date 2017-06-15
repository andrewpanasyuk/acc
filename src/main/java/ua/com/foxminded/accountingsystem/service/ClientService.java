package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Client;

import java.util.List;

public interface ClientService {

    void delete(Client client);

    Client save(Client client);

    Client findOne(Long id);

    List<Client> findAll();
}
