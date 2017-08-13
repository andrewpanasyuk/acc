package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.PersonalAccountMoneyTransfer;
import ua.com.foxminded.accountingsystem.repository.MoneyRepository;
import ua.com.foxminded.accountingsystem.repository.PersonalAccountMoneyTransferRepository;
import ua.com.foxminded.accountingsystem.service.PersonalAccountMoneyTransferService;
import ua.com.foxminded.accountingsystem.service.exception.NotEnoughMoneyException;

import javax.transaction.Transactional;

@Service
public class PersonalAccountMoneyTransferServiceJPA implements PersonalAccountMoneyTransferService {


    private final PersonalAccountMoneyTransferRepository moneyTransferRepository;
    private final MoneyRepository moneyRepository;

    @Autowired
    public PersonalAccountMoneyTransferServiceJPA(PersonalAccountMoneyTransferRepository moneyTransferRepository, MoneyRepository moneyRepository) {
        this.moneyTransferRepository = moneyTransferRepository;
        this.moneyRepository = moneyRepository;
    }

    @Override
    @Transactional
    public void withdraw(Long accountMoneyId, PersonalAccountMoneyTransfer moneyTransfer) {

        Money currentBalance = moneyRepository.findOne(accountMoneyId);

        long withdrawValue = Math.abs(moneyTransfer.getMoney().getAmount());

        if (currentBalance.getAmount() > 0 && currentBalance.getAmount() >= withdrawValue){
            currentBalance.setAmount(currentBalance.getAmount() - withdrawValue);
            moneyRepository.save(currentBalance);
            moneyTransferRepository.save(moneyTransfer);
        } else {
            throw new NotEnoughMoneyException("Withdraw rejected. Have no money for this transaction.");
        }
    }

    @Override
    public void addFunds(PersonalAccountMoneyTransfer deposit) {
        System.out.println("addFunds" + deposit);
    }
}
