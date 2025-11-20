package org.example.projet_eva_doumbengangue.service;

import org.example.projet_eva_doumbengangue.entity.Client;
import org.example.projet_eva_doumbengangue.respository.ClientRespository;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private ClientRespository clientRespository;

    public List<Client> getAllClients() {
        return clientRespository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        return clientRespository.findById(id).orElseThrow(() -> new RuntimeException("le client existe pas "));
    }

    @Override
    public Client createClient(Client client) {
        return clientRespository.save(client);
    }

    @Override
    public Client updateClient(Long id ,Client client) {
        Client existing = getClientById(id);

        existing.setNom(client.getNom());
        existing.setPrenom(client.getPrenom());
        existing.setAdresse(client.getAdresse());
        existing.setCodePostal(client.getCodePostal());
        existing.setVille(client.getVille());
        existing.setTelephone(client.getTelephone());

        return clientRespository.save(existing);
    }

    @Override
    public void deleteClient(Long id) {
        Client pop = getClientById(id);
        clientRespository.delete(pop);
    }

}
