package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.service.dto.ClientStatisticsDto;

import java.util.List;

public interface ClientService {

    void delete(Client client);

    void delete(Long id);

    Client create(Client client);

    Client update(Client client);

    Client findOne(Long id);

    List<Client> findAll();

    ClientStatisticsDto getClientStatistics();

}
