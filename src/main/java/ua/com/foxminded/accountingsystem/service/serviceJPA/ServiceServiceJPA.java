package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.repository.ServiceRepository;
import ua.com.foxminded.accountingsystem.service.ServiceService;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceJPA implements ServiceService {

    private static final Logger log = LoggerFactory.getLogger(ServiceServiceJPA.class);

    private final ServiceRepository repository;

    @Autowired
    public ServiceServiceJPA(ServiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Service> findAll() {
        return repository.findAll();
    }

    @Override
    public Service save(Service service) {
        return repository.save(service);
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }

    @Override
    public Service findOne(long id) {
        return repository.findOne(id);
    }

    @Override
    public Service prepareNewService() {
        Service service = new Service();
        for (Currency currency : Currency.values()) {
            service.getPrices().add(new Money(0, currency));
        }
        service.setEmployeeRate(new Money(0, Currency.UAH));
        return service;
    }

}
