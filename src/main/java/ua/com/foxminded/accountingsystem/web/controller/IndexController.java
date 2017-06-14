package ua.com.foxminded.accountingsystem.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.foxminded.accountingsystem.model.User;
import ua.com.foxminded.accountingsystem.service.UserService;

@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    private UserService userService;

    @Autowired
    public IndexController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("title", "Foxminded Accounting System");
        return "index";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        userService.create(user);
        logger.info("Saved user with username {} and email {}", user.getUsername(), user.getEmail());
        redirectAttributes.addFlashAttribute("username", user.getUsername());
        return "redirect:/welcome.html";
    }

}
