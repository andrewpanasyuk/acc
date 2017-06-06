package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Service;

import java.util.List;

/**
 * Created by Andrew on 04.06.2017.
 */
public interface ServiceService {
    List<Service> getAllServices();

    public Service save(Service service);

    public void remove(Long id);

    public Service getById(Long id);
}
