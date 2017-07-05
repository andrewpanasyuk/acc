package ua.com.foxminded.accountingsystem.web.controller.mentor;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.foxminded.accountingsystem.service.EmployeeService;

@Controller
@RequestMapping("/mentor")
public class MentorIndexController {
    private final EmployeeService empployeeService;
    
    @Autowired
    public MentorIndexController(EmployeeService empployeeService) {
        super();
        this.empployeeService = empployeeService;
    }

    @GetMapping
    public String getMentorIndex(Principal principal,Model model) {
        model.addAttribute("title", "Mentor panel");
        model.addAttribute("mentor", empployeeService.findByUsername(principal.getName()));
        
        return "mentor/index";
    }

}
