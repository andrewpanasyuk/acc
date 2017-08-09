package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.PersonalAccountMoneyTransferHistory;

/**
 * Created by andreb on 09.08.17.
 */
public interface PersonalAccountMoneyTransferHistoryService {

    void makeWithdraw(Money money, PersonalAccountMoneyTransferHistory transferHistory);
}
