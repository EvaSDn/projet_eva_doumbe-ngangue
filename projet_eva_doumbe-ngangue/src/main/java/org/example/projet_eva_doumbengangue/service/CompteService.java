package org.example.projet_eva_doumbengangue.service;

import org.example.projet_eva_doumbengangue.entity.Compte;

import java.util.List;


public interface CompteService {

    public List<Compte> getAllComptes();
    public Compte getCompteById(Long id);
    public List<Compte> getComptesByClient(Long clientId);
    public Compte createCompte(Long clientId, Compte compte);
    public Compte updateCompte(Long id, Compte compte);
    public void deleteCompte(Long id);
    public void virement(Long idSource, Long idCible, double montant);
    public void appliquerInterets(Long idCompte);
    public void deleteComptesByClient(Long clientId);
}
