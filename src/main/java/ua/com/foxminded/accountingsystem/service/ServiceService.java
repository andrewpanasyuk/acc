package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Service;

import java.util.List;

public interface ServiceService {

    List<Service> findAll();

    Service save(Service service);

    void delete(long id);

    Service findOne(long id);

    Service prepareNewService();
}
