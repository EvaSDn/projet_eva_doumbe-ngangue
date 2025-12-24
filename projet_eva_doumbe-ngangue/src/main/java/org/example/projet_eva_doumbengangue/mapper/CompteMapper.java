package org.example.projet_eva_doumbengangue.mapper;

import org.example.projet_eva_doumbengangue.dto.CompteDTO;
import org.example.projet_eva_doumbengangue.entity.Client;
import org.example.projet_eva_doumbengangue.entity.Compte;
import org.example.projet_eva_doumbengangue.respository.ClientRespository;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {

    private final ClientRespository clientRespository;

    public CompteMapper(ClientRespository clientRespository) {
        this.clientRespository = clientRespository;
    }

    public Compte toEntity(CompteDTO dto) {
        if (dto == null) return null;

        Compte compte = new Compte();
        compte.setNumeroCompte(dto.numeroCompte());
        compte.setSolde(dto.solde());
        compte.setDateOuverture(dto.dateOuverture());
        compte.setTypeCompte(dto.typeCompte());

        if (dto.clientId() != null) {
            Client client = clientRespository.findById(dto.clientId())
                    .orElseThrow(() -> new RuntimeException("Client introuvable"));
            compte.setClient(client);
        }

        return compte;
    }

    public CompteDTO toDTO(Compte compte) {
        if (compte == null) return null;

        Long clientId = compte.getClient() != null ? compte.getClient().getId() : null;

        return new CompteDTO(
                compte.getId(),
                compte.getNumeroCompte(),
                compte.getSolde(),
                compte.getDateOuverture(),
                compte.getTypeCompte(),
                clientId
        );
    }
}
