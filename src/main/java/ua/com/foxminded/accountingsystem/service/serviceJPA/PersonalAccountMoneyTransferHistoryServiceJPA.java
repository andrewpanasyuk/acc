package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.exception.WithdrawException;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.PersonalAccountMoneyTransferHistory;
import ua.com.foxminded.accountingsystem.repository.PersonalAccountMoneyTransferHistoryRepository;
import ua.com.foxminded.accountingsystem.service.PersonalAccountMoneyTransferHistoryService;

import javax.transaction.Transactional;

/**
 * Created by andreb on 09.08.17.
 */
@Service
public class PersonalAccountMoneyTransferHistoryServiceJPA implements PersonalAccountMoneyTransferHistoryService {

    private final PersonalAccountMoneyTransferHistoryRepository accountTransferHistoryRepository;

    public PersonalAccountMoneyTransferHistoryServiceJPA(PersonalAccountMoneyTransferHistoryRepository accountTransferHistoryRepository) {
        this.accountTransferHistoryRepository = accountTransferHistoryRepository;
    }

    @Override
    @Transactional
    public void makeWithdraw(Money withdraw, PersonalAccountMoneyTransferHistory transferHistory) {

        Money oldValue = accountTransferHistoryRepository
            .findMoneyById(withdraw.getId());

        if (oldValue.getAmount() > 0 && oldValue.getAmount() >= withdraw.getAmount()){
            long newAmount = oldValue.getAmount() - withdraw.getAmount();
            accountTransferHistoryRepository.updateMoney(withdraw.getId(), withdraw.getCurrency(), newAmount);
            transferHistory.getMoney().setAmount(newAmount);
            transferHistory.getMoney().setCurrency(withdraw.getCurrency());
            accountTransferHistoryRepository.save(transferHistory);
        }else{
            throw new WithdrawException("Withdraw rejected. Have no money for this transaction.");
        }
    }
}
