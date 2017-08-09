package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.PersonalAccountMoneyTransfer;
import ua.com.foxminded.accountingsystem.repository.PersonalAccountMoneyTransferRepository;
import ua.com.foxminded.accountingsystem.service.PersonalAccountMoneyTransferService;
import ua.com.foxminded.accountingsystem.service.exception.NotEnoughMoneyWithdrawException;

import javax.transaction.Transactional;

@Service
public class PersonalAccountMoneyTransferServiceJPA implements PersonalAccountMoneyTransferService {

    private final PersonalAccountMoneyTransferRepository moneyTransferRepository;

    public PersonalAccountMoneyTransferServiceJPA(
        PersonalAccountMoneyTransferRepository moneyTransferRepository) {
        this.moneyTransferRepository = moneyTransferRepository;
    }

    @Override
    @Transactional
    public void makeWithdraw(Long accountMoneyId,
                             PersonalAccountMoneyTransfer moneyTransfer) {

        Long currentBalanceValue = moneyTransferRepository
            .findMoneyById(accountMoneyId).getAmount();

        long withdrawValue = moneyTransfer.getMoney().getAmount();

        if (currentBalanceValue > 0 && currentBalanceValue >= withdrawValue){
            long newAmount = currentBalanceValue - withdrawValue;
            moneyTransferRepository.updateMoney(accountMoneyId, newAmount);
            moneyTransferRepository.save(moneyTransfer);
        } else {
            throw new NotEnoughMoneyWithdrawException("Withdraw rejected. Have no money for this transaction.");
        }
    }
}
