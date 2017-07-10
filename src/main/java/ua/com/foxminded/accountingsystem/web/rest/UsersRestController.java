package ua.com.foxminded.accountingsystem.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.foxminded.accountingsystem.model.User;
import ua.com.foxminded.accountingsystem.service.UserService;
import ua.com.foxminded.accountingsystem.service.dto.UserDto;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UsersRestController {

    private static final Logger log = LoggerFactory.getLogger(UsersRestController.class);

    private final UserService userService;

    @Autowired
    public UsersRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, Errors errors) throws URISyntaxException {

        if (errors.hasErrors()) {
            String errMessage = errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(";"));
            log.debug("Register errors: " + errMessage);
            return ResponseEntity.badRequest().body(errMessage);

        }

        if (userService.findByUsername(user.getUsername()) != null){
            String errMessage = "Username already exist";
            log.debug("Register errors: " + errMessage);
            return ResponseEntity.badRequest().body(errMessage);
        }

        User createdUser = userService.create(user);
        log.info("User created: " + user);
        return ResponseEntity.created(URI.create("/api/users/" + createdUser.getUsername())).body(createdUser);
    }
}
