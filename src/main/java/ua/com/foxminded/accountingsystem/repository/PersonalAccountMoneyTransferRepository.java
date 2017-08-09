package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.PersonalAccountMoneyTransfer;

public interface PersonalAccountMoneyTransferRepository extends JpaRepository<PersonalAccountMoneyTransfer, Long> {

    @Query("select money from Money money where money.id = ?1")
    Money findMoneyById(Long id);

    @Modifying
    @Query(value = "UPDATE Money SET amount = ?2 where id = ?1")
    void updateMoney(Long id, long amount);
}
