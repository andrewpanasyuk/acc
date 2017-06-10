package ua.com.foxminded.accountingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;

import java.util.List;

/**
 * Created by Dmytro Kushnir on 03.06.17.
 */
@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }


    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }


    @Override
    public List<Contract> getAllContractsByOrder(Order order) {
        return null;
    }


    @Override
    public Contract getContractById(Long id) {
        return contractRepository.getOne(id);
    }

    @Override
    public void remove(Long id) {
        contractRepository.delete(id);
    }

    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }
}
