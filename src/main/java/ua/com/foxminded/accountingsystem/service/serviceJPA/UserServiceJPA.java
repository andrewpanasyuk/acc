package ua.com.foxminded.accountingsystem.service.serviceJPA;

/**
 * Created by andreb on 10.07.17.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.User;
import ua.com.foxminded.accountingsystem.model.UserRole;
import ua.com.foxminded.accountingsystem.repository.UserRepository;
import ua.com.foxminded.accountingsystem.repository.UserRoleRepository;
import ua.com.foxminded.accountingsystem.service.UserService;
import ua.com.foxminded.accountingsystem.service.dto.UserDto;

import java.util.List;

import static ua.com.foxminded.accountingsystem.service.dto.converter.UserConverter.convertToDtoList;

@Service
public class UserServiceJPA implements UserService{

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceJPA(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserDto> findAll() {
        List<UserDto> userDtoList = convertToDtoList(userRepository.findAll());
        return userDtoList;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User create(User user) {
        user.setEnabled(true);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserRole userRole = userRoleRepository.findByRole("USER");
        if (userRole == null) {
            userRole = new UserRole();
            userRole.setRole("USER");
        }
        user.addRole(userRole);
        return userRepository.save(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
