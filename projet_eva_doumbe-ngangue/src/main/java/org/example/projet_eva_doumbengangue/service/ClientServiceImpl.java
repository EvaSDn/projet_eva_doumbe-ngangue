package org.example.projet_eva_doumbengangue.service;

import org.example.projet_eva_doumbengangue.entity.Client;
import org.example.projet_eva_doumbengangue.respository.ClientRespository;
import org.example.projet_eva_doumbengangue.respository.CompteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private ClientRespository clientRespository;
    //private CompteService compteService;
private CompteRepository compteRepository;
    public ClientServiceImpl(ClientRespository clientRespository,  CompteRepository compteRepository) {

        this.clientRespository = clientRespository;
       // this.compteService = compteService;
        this.compteRepository = compteRepository;
    }
     @Override
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
        if (client == null) {
            return null;
        }
        Client personne = getClientById(id);

        personne.setNom(client.getNom());
        personne.setPrenom(client.getPrenom());
        personne.setCodePostal(client.getCodePostal());
        personne.setVille(client.getVille());
        personne.setTelephone(client.getTelephone());

        return clientRespository.save(personne);
    }

    @Override
    public void deleteClient(Long id) {
        Client pop = getClientById(id);
       // compteService.deleteComptesByClient(id);
        compteRepository.deleteByClientId(id);
        clientRespository.delete(pop);
    }

    @Override
    public List<Client> searchByPrenomAndNom(String prenom, String nom) {
        return clientRespository.findByPrenomAndNom(prenom, nom);
    }

}
