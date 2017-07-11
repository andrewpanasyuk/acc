package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.User;
import ua.com.foxminded.accountingsystem.service.dto.UserDto;
import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    User findByUsername(String username);

    User create(User user);

    User save(User user);

}
