package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.PersonalAccount;
import ua.com.foxminded.accountingsystem.model.PersonalAccountMoneyTransferHistory;
import ua.com.foxminded.accountingsystem.model.TransferType;
import ua.com.foxminded.accountingsystem.service.ClientService;
import ua.com.foxminded.accountingsystem.service.PersonalAccountMoneyTransferHistoryService;

@Controller
@RequestMapping("/admin/clients")
public class AdminClientController {

    private final ClientService clientService;
    private final PersonalAccountMoneyTransferHistoryService accountTransferHistoryService;

    @Autowired
    public AdminClientController(ClientService clientService,
                                 PersonalAccountMoneyTransferHistoryService accountTransferHistoryService) {

        this.clientService = clientService;
        this.accountTransferHistoryService = accountTransferHistoryService;
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
    public String withdraw(@ModelAttribute Money money,
                           @RequestParam Long accountId,
                           @RequestParam String description,
                           @RequestParam Long clientId) {

        PersonalAccountMoneyTransferHistory withdraw = new PersonalAccountMoneyTransferHistory();
        withdraw.setTransferType(TransferType.OUTCOME);
        PersonalAccount account = new PersonalAccount();
        account.setId(accountId);
        withdraw.setPersonalAccount(account);
        withdraw.setDescription(description);

        accountTransferHistoryService.makeWithdraw(money, withdraw);

        return "redirect:/admin/clients/" + clientId;
    }
}
