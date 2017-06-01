package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.foxminded.accountingsystem.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model
            .addAttribute("title", "Users management")
            .addAttribute("users", userService.findAll());

        return "admin/users";
    }

    @GetMapping("/{username}")
    public String getOneUser(@PathVariable String username, Model model) {
        model
            .addAttribute("title", username + " management")
            .addAttribute("user", userService.findByUsername(username));
        return "admin/user";
    }

}
