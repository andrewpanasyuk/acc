package ua.com.foxminded.accountingsystem.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String getIndex(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("username", model.asMap().get("username"));
        return "welcome";
    }
}
