package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;

public interface MoneyRepository extends JpaRepository<Money, Long> {

    @Query("select money from Money money inner join PersonalAccount account where account.money.currency = ?2 and account.id = ?1")
    Money findMoneyByPeronalAccountId(Long id, Currency currency);
}
