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
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }


    @Override
    public Contract findOne(Long id) {
        return contractRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        contractRepository.delete(id);
    }

    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }
}
