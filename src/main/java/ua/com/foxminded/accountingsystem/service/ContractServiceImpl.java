package ua.com.foxminded.accountingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;

import java.time.LocalDate;
import java.util.List;

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

    @Override
    public Contract create(Order order){

        Contract contract = new Contract();

        Money employeeRate = new Money();
        employeeRate.setPrice(order.getService().getEmployeeRate().getPrice());
        employeeRate.setCurrency(Currency.UAH);

        Money price = new Money();
        for (Money curPrice: order.getService().getPrices()) {
            if (curPrice.getCurrency() == Currency.UAH){
                price.setPrice(curPrice.getPrice());
            }
        }
        price.setCurrency(Currency.UAH);

        contract.setOrder(order);
        contract.setContractDate(LocalDate.now());
        contract.setEmployeeRate(employeeRate);
        contract.setPrice(price);

        return contract;

    }
}


