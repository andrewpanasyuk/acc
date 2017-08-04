package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.PersonalAccountMoneyTransferHistory;
import ua.com.foxminded.accountingsystem.service.ClientService;

@Controller
@RequestMapping("/admin/clients")
public class AdminClientController {
    private final ClientService clientService;

    @Autowired
    public AdminClientController(ClientService clientService) {
        this.clientService = clientService;
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
        Client client = clientService.findOne(id);
        model.addAttribute("client", client);
        PersonalAccountMoneyTransferHistory withdraw = new PersonalAccountMoneyTransferHistory();
        if (client != null){
            withdraw.setPersonalAccount(client.getPersonalAccount());
        }
        model.addAttribute("withdraw", withdraw);
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
    public String createClientField(@ModelAttribute PersonalAccountMoneyTransferHistory withdraw) {
        System.out.println("Withdraw: " + withdraw);
        return "redirect:/admin/client";
    }

}
