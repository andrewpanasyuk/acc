package ua.com.foxminded.accountingsystem.service;


import ua.com.foxminded.accountingsystem.model.Contract;

import java.util.List;

/**
 * Created by Dmytro Kushnir on 03.06.17.
 */
public interface ContractService {

    List<Contract> findAll();

    Contract findOne(Long id);

    void delete(Long id);

    Contract save(Contract contract);

}
