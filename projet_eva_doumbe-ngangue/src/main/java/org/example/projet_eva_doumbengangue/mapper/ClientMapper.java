package org.example.projet_eva_doumbengangue.mapper;

import org.example.projet_eva_doumbengangue.dto.ClientDTO;
import org.example.projet_eva_doumbengangue.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(ClientDTO dto) {
        if (dto == null) return null;

        Client client = new Client();
        client.setNom(dto.nom());
        client.setPrenom(dto.prenom());
        client.setTelephone(dto.telephone());
        client.setCodePostal(dto.codePostal());
        client.setVille(dto.ville());
        return client;
    }

    public ClientDTO toDTO(Client client) {
        if (client == null) return null;

        return new ClientDTO(
                client.getId(),
                client.getNom(),
                client.getPrenom(),
                client.getTelephone(),
                client.getCodePostal(),
                client.getVille()
        );
    }
}
