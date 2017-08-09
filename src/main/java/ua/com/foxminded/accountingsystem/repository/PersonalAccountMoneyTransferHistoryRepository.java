package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.PersonalAccountMoneyTransferHistory;

public interface PersonalAccountMoneyTransferHistoryRepository extends JpaRepository<PersonalAccountMoneyTransferHistory, Long> {

    @Query("select money from Money money where money.id = ?1")
    Money findMoneyById(Long id);

    @Modifying
    @Query(value = "UPDATE Money SET currency = ?2, amount = ?3 where id = ?1")
    void updateMoney(Long id, Currency currency, long amount);
}
