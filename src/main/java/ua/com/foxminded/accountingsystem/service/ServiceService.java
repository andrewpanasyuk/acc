package ua.com.foxminded.accountingsystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.repository.ServiceRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    private static final Logger log = LoggerFactory.getLogger(ServiceService.class);

    private final ServiceRepository repository;

    @Autowired
    public ServiceService(ServiceRepository repository) {
        this.repository = repository;
    }

    public List<Service> findAll(){
        return repository.findAll();
    }

    public Service save(Service service){
        return repository.save(service);
    }

    public void delete(long id){
        repository.delete(id);
    }

    public Service findById(long id){
        return repository.findOne(id);
    }

}
