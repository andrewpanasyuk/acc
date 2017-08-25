package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.service.dto.ClientStatisticsDto;
import ua.com.foxminded.accountingsystem.service.dto.ConsultancyStatisticsDto;
import ua.com.foxminded.accountingsystem.service.dto.DealOfClientDto;

import java.util.List;

public interface ClientService {

    void delete(Client client);

    void delete(Long id);

    Client create(Client client);

    Client update(Client client);

    Client findOne(Long id);

    List<Client> findAll();

    ClientStatisticsDto getClientStatistics();

    List<ConsultancyStatisticsDto> getConsultancyStatistics();

    List<DealOfClientDto> findDealsAndMentorsByClient(Long clientId);
}
