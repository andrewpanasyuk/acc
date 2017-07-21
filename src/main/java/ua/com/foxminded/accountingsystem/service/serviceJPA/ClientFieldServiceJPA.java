package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.accountingsystem.model.ClientField;
import ua.com.foxminded.accountingsystem.repository.ClientFieldRepository;
import ua.com.foxminded.accountingsystem.repository.ClientFieldValueRepository;
import ua.com.foxminded.accountingsystem.service.ClientFieldService;

import java.util.List;

@Service
public class ClientFieldServiceJPA implements ClientFieldService {

    private final ClientFieldRepository clientFieldRepository;
    private final ClientFieldValueRepository clientFieldValueRepository;

    @Autowired
    public ClientFieldServiceJPA(ClientFieldRepository clientFieldRepository, ClientFieldValueRepository clientFieldValueRepository) {
        this.clientFieldRepository = clientFieldRepository;
        this.clientFieldValueRepository = clientFieldValueRepository;
    }

    @Override
    public List<ClientField> findAll() {
        return clientFieldRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientFieldValueRepository.deleteByClientField_Id(id);
        clientFieldRepository.delete(id);
    }

    @Override
    @Transactional
    public ClientField create(ClientField clientField) {
        ClientField savedField = clientFieldRepository.saveAndFlush(clientField);
        clientFieldValueRepository.insertForAllClientsByClientFieldId(savedField.getId());
        return savedField;
    }

}
