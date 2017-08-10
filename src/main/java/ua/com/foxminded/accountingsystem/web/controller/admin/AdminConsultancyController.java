package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.foxminded.accountingsystem.model.Consultancy;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.service.ConsultancyService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/consultancies")
public class AdminConsultancyController {

    private static final Logger log = LoggerFactory.getLogger(AdminConsultancyController.class);

    private final ConsultancyService consultancyService;

    @Autowired
    public AdminConsultancyController(ConsultancyService consultancyService) {
        this.consultancyService = consultancyService;
    }

    @GetMapping
    public String getAllConsultancies(Model model) {
        List<Consultancy> consultancies = consultancyService.findAll();
        log.debug("Found: " + consultancies.size() + " consultancies!");
        model.addAttribute("consultancies", consultancies);
        return "admin/consultancies";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        log.debug("Remove consultancy with id: " + id);
        if (id <= 0) {
            return "redirect:/admin/consultancies";
        }
        consultancyService.delete(id);
        return "redirect:/admin/consultancies";
    }

    @GetMapping("/{id}")
    public String getOneConsultancy(@PathVariable long id, Model model) {
        model.addAttribute("consultancy", consultancyService.findOne(id));
        model.addAttribute("currencies", Currency.values());
        return "admin/consultancy";
    }

    @PostMapping
    public String save(@ModelAttribute @Valid Consultancy consultancy, BindingResult result) {
        log.debug("Try to createDealByClientId consultancy: " + consultancy);
        if (result.hasErrors()) {
            log.debug("Binding result: " + result.getAllErrors());
            return "admin/consultancy";
        }
        log.debug("Save consultancy: " + consultancy);
        consultancyService.save(consultancy);
        return "redirect:/admin/consultancies";
    }

    @GetMapping("/new")
    public String createNewConsultancy(Model model) {
        model.addAttribute("consultancy", consultancyService.prepareNewConsultancy());
        model.addAttribute("currencies", Currency.values());
        return "admin/consultancy";
    }

}
