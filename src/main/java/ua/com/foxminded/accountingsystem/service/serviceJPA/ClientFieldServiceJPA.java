package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.ClientField;
import ua.com.foxminded.accountingsystem.repository.ClientFieldRepository;
import ua.com.foxminded.accountingsystem.service.ClientFieldService;

import java.util.List;

@Service
public class ClientFieldServiceJPA implements ClientFieldService{
    private final ClientFieldRepository clientFieldRepository;

    @Autowired
    public ClientFieldServiceJPA(ClientFieldRepository clientFieldRepository){
        this.clientFieldRepository = clientFieldRepository;
    }

    @Override
    public List<ClientField> findAll() {
        return clientFieldRepository.findAll();
    }
}
