package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.PersonalAccount;

public interface PersonalAccountRepository extends JpaRepository<PersonalAccount, Long> {
}
