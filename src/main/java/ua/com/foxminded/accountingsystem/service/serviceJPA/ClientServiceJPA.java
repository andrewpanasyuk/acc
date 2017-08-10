package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.ClientField;
import ua.com.foxminded.accountingsystem.model.ClientFieldValue;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.repository.ClientRepository;
import ua.com.foxminded.accountingsystem.repository.DealRepository;
import ua.com.foxminded.accountingsystem.repository.ServiceRepository;
import ua.com.foxminded.accountingsystem.service.ClientFieldService;
import ua.com.foxminded.accountingsystem.service.ClientService;
import ua.com.foxminded.accountingsystem.service.dto.ClientStatisticsDto;
import ua.com.foxminded.accountingsystem.service.dto.ServiceStatisticsDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceJPA implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientFieldService clientFieldService;
    private final ServiceRepository serviceRepository;
    private final DealRepository dealRepository;

    @Autowired
    public ClientServiceJPA(ClientRepository clientRepository, ClientFieldService clientFieldService,
                            ServiceRepository serviceRepository, DealRepository dealRepository) {
        this.clientRepository = clientRepository;
        this.clientFieldService = clientFieldService;
        this.serviceRepository = serviceRepository;
        this.dealRepository = dealRepository;
    }

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.delete(id);
    }

    @Override
    public Client create(Client client) {
        List<ClientField> clientFields = clientFieldService.findAll();
        if(client.getExtraFields() == null){
            client.setExtraFields(new ArrayList<>());
        }

        Set<ClientField> clientFieldsOfCurrentClient = client.getExtraFields().stream()
            .map(ClientFieldValue::getClientField).collect(Collectors.toSet());

        for(ClientField clientField: clientFields){
            if(!clientFieldsOfCurrentClient.contains(clientField)){
                ClientFieldValue clientFieldValue = new ClientFieldValue();
                clientFieldValue.setClientField(clientField);
                client.addClientFieldValue(clientFieldValue);
            }
        }

        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client){
        return clientRepository.save(client);
    }

    @Override
    public Client findOne(Long id) {
        return clientRepository.findOne(id);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public ClientStatisticsDto getClientStatistics() {

        ClientStatisticsDto statistics = new ClientStatisticsDto();

        statistics.setActiveClients(clientRepository
            .countClientByDealsStatus(DealStatus.ACTIVE));

        statistics.setAllClients(clientRepository.count());

        statistics.setNewClientsForLastMonth(clientRepository
            .countClientByCreatedDateAfter(LocalDateTime.now().minusMonths(1)));

        statistics.setFrozenClients(clientRepository
            .countFrozenClients());

        statistics.setFrozenClientsForLastMonth(0L); //TODO Define value

        statistics.setGraduatedClients(clientRepository
            .countClientByDealsStatus(DealStatus.COMPLETED));

        statistics.setGraduatedClientsForLastMonth(clientRepository
            .countClientByDealsStatusAndDealsCloseDateAfter(DealStatus.COMPLETED, LocalDate.now().minusMonths(1)));

        return statistics;
    }

    @Override
    public List<ServiceStatisticsDto> getServiceStatistics() {

        List<ServiceStatisticsDto> statistics = new ArrayList<>();
        List<ua.com.foxminded.accountingsystem.model.Service> serviceList = serviceRepository.findAll();

        for(ua.com.foxminded.accountingsystem.model.Service service : serviceList){
            ServiceStatisticsDto serviceStatisticsDto = new ServiceStatisticsDto();
            serviceStatisticsDto.setServiceName(service.getName());
            serviceStatisticsDto.setCountActiveCases(dealRepository.countDealsByStatusAndService(DealStatus.ACTIVE, service));
            serviceStatisticsDto.setCountFrozenCases(dealRepository.countDealsByStatusAndService(DealStatus.FROZEN, service));
            serviceStatisticsDto.setCountCompletedCases(dealRepository.countDealsByStatusAndService(DealStatus.COMPLETED, service));
            statistics.add(serviceStatisticsDto);
        }

        return statistics;
    }

}
