package org.example.projet_eva_doumbengangue.service;

import org.example.projet_eva_doumbengangue.entity.Client;

import java.util.List;

public interface ClientService {
    public List<Client> getAllClients();
    public Client getClientById(Long id);
    public Client createClient(Client client);
    public Client updateClient(Long id,Client client);
    public void deleteClient(Long id);
    public List<Client> searchByPrenomAndNom(String prenom, String nom);

}
