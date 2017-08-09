package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.PersonalAccountMoneyTransfer;

public interface PersonalAccountMoneyTransferService {

    void makeWithdraw(Long accountMoneyId, PersonalAccountMoneyTransfer moneyTransfer);
}
