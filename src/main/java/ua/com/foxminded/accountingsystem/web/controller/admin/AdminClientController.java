package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.PersonalAccountMoneyTransfer;
import ua.com.foxminded.accountingsystem.service.ClientService;
import ua.com.foxminded.accountingsystem.service.PersonalAccountMoneyTransferService;
import ua.com.foxminded.accountingsystem.service.exception.NotEnoughMoneyException;

@Controller
@RequestMapping("/admin/clients")
public class AdminClientController {

    private static final Logger log = LoggerFactory.getLogger(AdminClientController.class);

    private final ClientService clientService;
    private final PersonalAccountMoneyTransferService moneyTransferService;

    @Autowired
    public AdminClientController(ClientService clientService,
                                 PersonalAccountMoneyTransferService moneyTransferService) {

        this.clientService = clientService;
        this.moneyTransferService = moneyTransferService;
    }

    @GetMapping
    public String getAllClients(Model model) {
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("client", new Client());
        return "admin/clients";
    }

    @PostMapping
    public String create(@ModelAttribute Client client) {
        Client savedClient = clientService.create(client);
        return "redirect:/admin/clients/" + savedClient.getId();
    }

    @PutMapping(value = "{id}")
    public String update(@PathVariable long id, @ModelAttribute("client") Client client) {
        clientService.create(client);
        return "redirect:/admin/clients";
    }

    @GetMapping("/{id}")
    public String getClientByID(@PathVariable long id, Model model) {
        model.addAttribute("client", clientService.findOne(id));
        return "admin/client";
    }

    @DeleteMapping("/{id}")
    public String removeClient(@PathVariable long id) {
        clientService.delete(id);
        return "redirect:/admin/clients";
    }

    @GetMapping(value = "/create")
    public String addClient(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "admin/client";
    }

    @PostMapping("/withdraw")
    public String withdraw(@ModelAttribute PersonalAccountMoneyTransfer withdraw, @RequestParam Long clientId,
                           RedirectAttributes redirectAttributes) {

        try{
            moneyTransferService.withdraw(withdraw);
        } catch (NotEnoughMoneyException e){
            redirectAttributes.addFlashAttribute("transferError", e.getMessage());
        }

        return "redirect:/admin/clients/" + clientId;
    }

    @PostMapping("/deposit")
    public String deposit(@ModelAttribute PersonalAccountMoneyTransfer deposit, @RequestParam Long clientId) {

        moneyTransferService.deposit(deposit);
        return "redirect:/admin/clients/" + clientId;
    }
}
