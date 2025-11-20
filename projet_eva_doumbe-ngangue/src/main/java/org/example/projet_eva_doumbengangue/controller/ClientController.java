package org.example.projet_eva_doumbengangue.controller;

import lombok.RequiredArgsConstructor;
import org.example.projet_eva_doumbengangue.entity.Client;
import org.example.projet_eva_doumbengangue.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class ClientController {
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping("createClient")
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }
    @PutMapping("updateClient/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client ) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("deleteClient/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/search")
    public List<Client> serachClientByNames(String prenom, String nom){
        return clientService.searchByPrenomAndNom(prenom, nom);
    }


}
