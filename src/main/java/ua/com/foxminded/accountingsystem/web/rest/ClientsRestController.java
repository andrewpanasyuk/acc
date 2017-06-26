package ua.com.foxminded.accountingsystem.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.service.ClientService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientsRestController {

    private static final Logger log = LoggerFactory.getLogger(ClientsRestController.class);

    private final ClientService clientService;

    @Autowired
    public ClientsRestController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<Client> getAllClients(){
        return clientService.findAll();
    }

    @PostMapping("/clients/create")
    public ResponseEntity<Client> create(@RequestBody Client client){
        Client createdClient = clientService.create(client);
        log.info("Client created: " + createdClient);
        return ResponseEntity.created(URI.create("/api/clients/" + createdClient.getId())).body(createdClient);
    }

}
