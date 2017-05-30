package ua.com.foxminded.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
