package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.service.ClientFieldService;
import ua.com.foxminded.accountingsystem.service.ClientService;

import java.util.List;

@Controller
@RequestMapping("/admin/clients")
public class AdminClientController {
    private final ClientService clientService;
    private final ClientFieldService clientFieldService;

    @Autowired
    public AdminClientController(ClientService clientService, ClientFieldService clientFieldService) {
        this.clientService = clientService;
        this.clientFieldService = clientFieldService;
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

}
