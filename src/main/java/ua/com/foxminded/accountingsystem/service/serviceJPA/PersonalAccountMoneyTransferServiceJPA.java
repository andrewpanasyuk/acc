package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.PersonalAccount;
import ua.com.foxminded.accountingsystem.model.PersonalAccountMoneyTransfer;
import ua.com.foxminded.accountingsystem.repository.MoneyRepository;
import ua.com.foxminded.accountingsystem.repository.PersonalAccountMoneyTransferRepository;
import ua.com.foxminded.accountingsystem.repository.PersonalAccountRepository;
import ua.com.foxminded.accountingsystem.service.PersonalAccountMoneyTransferService;
import ua.com.foxminded.accountingsystem.service.exception.NotEnoughMoneyException;

import javax.transaction.Transactional;

@Service
public class PersonalAccountMoneyTransferServiceJPA implements PersonalAccountMoneyTransferService {


    private final PersonalAccountMoneyTransferRepository moneyTransferRepository;
    private final MoneyRepository moneyRepository;
    private final PersonalAccountRepository personalAccountRepository;

    @Autowired
    public PersonalAccountMoneyTransferServiceJPA(PersonalAccountMoneyTransferRepository moneyTransferRepository, MoneyRepository moneyRepository, PersonalAccountRepository personalAccountRepository) {
        this.moneyTransferRepository = moneyTransferRepository;
        this.moneyRepository = moneyRepository;
        this.personalAccountRepository = personalAccountRepository;
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
    @Transactional
    public void addFunds(PersonalAccountMoneyTransfer deposit) {
        PersonalAccount account = personalAccountRepository.findOne(deposit.getPersonalAccount().getId());
        System.out.println("Personal acc: " + account);
        for (Money money  : account.getMoney()){
            System.out.println("Money: " + money);
            if (money.getCurrency().equals(deposit.getMoney().getCurrency())){
                money.setAmount(money.getAmount() + Math.abs(deposit.getMoney().getAmount()));
                moneyRepository.save(money);
                moneyTransferRepository.save(deposit);
                break;
            }
        }
    }
}
