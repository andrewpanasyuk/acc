package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.PersonalAccountMoneyTransfer;

public interface PersonalAccountMoneyTransferService {

    void withdraw(PersonalAccountMoneyTransfer withdraw);

    void deposit(PersonalAccountMoneyTransfer deposit);
}
