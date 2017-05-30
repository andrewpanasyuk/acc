package ua.com.foxminded.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {

    @GetMapping
    public String getAdminIndex(Model model) {
        model.addAttribute("title", "Admin panel");
        return "admin/index";
    }

}
