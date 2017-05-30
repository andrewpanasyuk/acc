package ua.com.foxminded.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("title", "Foxminded Accounting System");
        return "index";
    }

}
