package org.example.projet_eva_doumbengangue.controller;

import lombok.RequiredArgsConstructor;
import org.example.projet_eva_doumbengangue.dto.ClientDTO;
import org.example.projet_eva_doumbengangue.dto.CompteDTO;
import org.example.projet_eva_doumbengangue.entity.Client;
import org.example.projet_eva_doumbengangue.entity.Compte;
import org.example.projet_eva_doumbengangue.mapper.ClientMapper;
import org.example.projet_eva_doumbengangue.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")

public class ClientController {
    
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    //@Autowired
    public ClientController(ClientService clientService,
                            ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        List<ClientDTO> dtos = clients.stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return ResponseEntity.ok(clientMapper.toDTO(client));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO dto) {
        Client client = clientMapper.toEntity(dto);
        Client created = clientService.createClient(client);
        return ResponseEntity.ok(clientMapper.toDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id,
                                                  @RequestBody ClientDTO dto) {
        Client toUpdate = clientMapper.toEntity(dto);
        Client updated = clientService.updateClient(id, toUpdate);
        return ResponseEntity.ok(clientMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Client> serachClientByNames(
            @RequestParam String prenom,
            @RequestParam String nom
    ) {
        return clientService.searchByPrenomAndNom(prenom, nom);
    }

    @GetMapping("/{id}/comptes")
    public List<Compte> getComptesByClient(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return client.getComptes();
    }




}
