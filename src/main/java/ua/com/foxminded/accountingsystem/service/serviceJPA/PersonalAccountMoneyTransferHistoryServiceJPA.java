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

    private final PersonalAccountMoneyTransferHistoryRepository transferHistoryRepository;

    public PersonalAccountMoneyTransferHistoryServiceJPA(PersonalAccountMoneyTransferHistoryRepository transferHistoryRepository) {
        this.transferHistoryRepository = transferHistoryRepository;
    }

    @Override
    @Transactional
    public void save(PersonalAccountMoneyTransferHistory transferHistory) {

        Money oldValue = transferHistoryRepository
            .findMoneyById(transferHistory.getMoney().getId());

        Money newValue = transferHistory.getMoney();

        System.out.println("Withdraw: " + transferHistory);
        System.out.println("oldValue: " + oldValue);
        System.out.println("NewValue: " + newValue);

        if (newValue.getAmount() <= oldValue.getAmount()){
            transferHistoryRepository.updateMoney(newValue.getId(), newValue.getCurrency(), newValue.getAmount());
            transferHistory.getMoney().setId(0);
            transferHistoryRepository.save(transferHistory);
            System.out.println("!!!!!!!!!!!!!!!!!!!");
        }else{
            System.out.println("*******************");
        }
    }
}
