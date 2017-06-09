package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.repository.ServiceRepository;
import ua.com.foxminded.accountingsystem.service.ServiceService;

import java.util.List;


@org.springframework.stereotype.Service
public class ServiceServiceJPA implements ServiceService {
    private static final Logger logger = LoggerFactory.getLogger(ServiceService.class);

    private ServiceRepository repository;

    @Autowired
    public void setClientRepository(ServiceRepository repository) {
        this.repository = repository;
    }

    public List<Service> getAllServices(){
        return repository.findAll();
    }
    public Service save(Service service){
        return repository.save(service);
    }
    public void remove(Long id){
        repository.delete(id);
    }
    public Service getById(Long id){
        return repository.findOne(id);
    }
}
