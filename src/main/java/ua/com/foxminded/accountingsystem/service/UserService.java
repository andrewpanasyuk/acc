package ua.com.foxminded.accountingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.User;
import ua.com.foxminded.accountingsystem.model.UserRole;
import ua.com.foxminded.accountingsystem.repository.UserRepository;
import ua.com.foxminded.accountingsystem.repository.UserRoleRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void create(User user) {
        user.setEnabled(true);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserRole userRole = userRoleRepository.findByRole("USER");
        if (userRole == null) {
            userRole = new UserRole();
            userRole.setRole("USER");
        }
        user.addRole(userRole);
        userRepository.save(user);
    }
}
