package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
