package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.stereotype.Service;
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
    public void makeWithdraw(PersonalAccountMoneyTransferHistory transferHistory) {

        Money oldValue = accountTransferHistoryRepository
            .findMoneyById(transferHistory.getMoney().getId());

        Money withdraw = transferHistory.getMoney();

        if (withdraw.getAmount() <= oldValue.getAmount()){
            long newAmount = oldValue.getAmount() - withdraw.getAmount();
            accountTransferHistoryRepository.updateMoney(withdraw.getId(), withdraw.getCurrency(), newAmount);
            transferHistory.getMoney().setId(0);
            accountTransferHistoryRepository.save(transferHistory);
        }else{

        }
    }
}
