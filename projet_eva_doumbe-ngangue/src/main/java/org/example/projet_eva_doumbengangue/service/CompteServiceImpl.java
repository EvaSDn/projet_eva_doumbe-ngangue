package org.example.projet_eva_doumbengangue.service;

import org.example.projet_eva_doumbengangue.entity.Client;
import org.example.projet_eva_doumbengangue.entity.Compte;
import org.example.projet_eva_doumbengangue.respository.ClientRespository;
import org.example.projet_eva_doumbengangue.respository.CompteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final ClientRespository clientRespository;

    public CompteServiceImpl(CompteRepository compteRepository,
                             ClientRespository clientRespository) {
        this.compteRepository = compteRepository;
        this.clientRespository = clientRespository;
    }

    @Override
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    @Override
    public Compte getCompteById(Long id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Le compte n'existe pas"));
    }

    @Override
    public Compte createCompte(Long clientId, Compte compte) {
        Client client = clientRespository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        compte.setClient(client);
        if (compte.getDateOuverture() == null) {
            compte.setDateOuverture(LocalDate.now());
        }
        if (compte.getSolde() == null) {
            compte.setSolde(0.0);
        }
        return compteRepository.save(compte);
    }

    @Override
    public Compte updateCompte(Long id, Compte compte) {
        Compte existing = getCompteById(id);
        existing.setNumeroCompte(compte.getNumeroCompte());
        existing.setSolde(compte.getSolde());
        existing.setTypeCompte(compte.getTypeCompte());
        return compteRepository.save(existing);
    }

    @Override
    public void deleteCompte(Long id) {
        Compte existing = getCompteById(id);
        compteRepository.delete(existing);
    }

    @Override
    public void virement(Long idSource, Long idCible, double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }

        Compte source = getCompteById(idSource);
        Compte cible = getCompteById(idCible);

        double nouveauSoldeSource = source.getSolde() - montant;

        if (source.getTypeCompte() == Compte.TypeCompte.COURANT) {
            if (nouveauSoldeSource < -1000) {
                throw new IllegalArgumentException("Découvert de 1000€ dépassé");
            }
        } else if (source.getTypeCompte() == Compte.TypeCompte.EPARGNE) {
            if (nouveauSoldeSource < 0) {
                throw new IllegalArgumentException("Solde insuffisant pour compte épargne");
            }
        }

        source.setSolde(nouveauSoldeSource);
        cible.setSolde(cible.getSolde() + montant);

        compteRepository.save(source);
        compteRepository.save(cible);
    }

    @Override
    public void appliquerInterets(Long idCompte) {
        Compte compte = getCompteById(idCompte);
        if (compte.getTypeCompte() == Compte.TypeCompte.EPARGNE) {
            double solde = compte.getSolde();
            double nouveauSolde = solde + solde * 0.03;
            compte.setSolde(nouveauSolde);
            compteRepository.save(compte);
        }
    }
    @Override
    public List<Compte> getComptesByClient(Long clientId) {
        return compteRepository.findByClientId(clientId);
    }
    public void deleteComptesByClient(Long clientId) {
        compteRepository.deleteByClientId(clientId);
    }
}
