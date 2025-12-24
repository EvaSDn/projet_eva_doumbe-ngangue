package org.example.projet_eva_doumbengangue.dto;

public record ClientDTO(Long id,
                        String nom,
                        String prenom,
                        String telephone,
                        String codePostal,
                        String ville) {
}
