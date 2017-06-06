package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.service.ClientService;

import java.time.LocalDate;
import java.util.List;

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
        List<Client> clients = clientService.getAllClients();
        model
            .addAttribute("clients", clients);
        return "admin/clients";
    }

    @GetMapping("/{id}")
    public String getClientByID(@PathVariable long id, Model model) {
        model
            .addAttribute("client", clientService.getClientById(id));
        return "admin/client";
    }

    @GetMapping(value = "/clientAdd")
    public String addClient(Model model) {
       Client client = new Client();
        model.addAttribute("client", client);
        return "admin/client";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute Client client) {
        clientService.addClient(client);
        return "redirect:/admin/clients";
    }

    @GetMapping(value = "/{id}/remove")
    public String removeOrder(@PathVariable long id) {
        Client client = clientService.getClientById(id);
        clientService.removeClient(client);
        return "redirect:/admin/clients";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute Client client) {
        clientService.updateClient(client);
        return "redirect:/admin/clients";
    }

}
