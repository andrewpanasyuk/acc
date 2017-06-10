package ua.com.foxminded.accountingsystem.service;


import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Order;

import java.util.List;

/**
 * Created by Dmytro Kushnir on 03.06.17.
 */
public interface ContractService {

    List<Contract> getAllContracts();

    List<Contract> getAllContractsByOrder(Order order);

    Contract getContractById(Long id);

    void remove(Long id);

    Contract save(Contract contract);

}
