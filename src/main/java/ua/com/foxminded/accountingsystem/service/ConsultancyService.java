package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Consultancy;

import java.util.List;

public interface ConsultancyService {

    List<Consultancy> findAll();

    Consultancy save(Consultancy consultancy);

    void delete(long id);

    Consultancy findOne(long id);

    Consultancy prepareNewConsultancy();
}
