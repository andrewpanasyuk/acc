package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.ClientField;
import ua.com.foxminded.accountingsystem.model.ClientFieldValue;
import ua.com.foxminded.accountingsystem.repository.ClientFieldRepository;
import ua.com.foxminded.accountingsystem.service.ClientFieldService;
import ua.com.foxminded.accountingsystem.service.ClientService;

import java.util.ArrayList;
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
        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "admin/clients";
    }

    @PutMapping
    public String update(@ModelAttribute Client client) {
        clientService.save(client);
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

        List<ClientField> clientFields = clientFieldService.findAll();
        client.setExtraFields(new ArrayList<>());
        for(ClientField clientField: clientFields){
            ClientFieldValue clientFieldValue = new ClientFieldValue();
            clientFieldValue.setClientField(clientField);
            client.addClientFieldValue(clientFieldValue);
        }

        model.addAttribute("client", client);
        return "admin/client";
    }

}
