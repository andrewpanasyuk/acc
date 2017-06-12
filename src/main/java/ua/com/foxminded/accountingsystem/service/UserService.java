package ua.com.foxminded.accountingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.User;
import ua.com.foxminded.accountingsystem.model.UserRole;
import ua.com.foxminded.accountingsystem.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void create(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(user.getUserRoles().size() == 0){
            UserRole userRole = new UserRole();
            userRole.setRole("USER");
            user.addRole(userRole);
        }
        userRepository.save(user);
    }
}
