package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.PersonalAccountMoneyTransfer;

public interface PersonalAccountMoneyTransferService {

    void withdraw(Long accountMoneyId, PersonalAccountMoneyTransfer moneyTransfer);

    void deposit(PersonalAccountMoneyTransfer deposit);
}
