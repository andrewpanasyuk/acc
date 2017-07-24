package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.ClientField;

import java.util.List;

public interface ClientFieldService {
    List<ClientField> findAll();

    void delete(Long id);

    ClientField create(ClientField clientField);
}
