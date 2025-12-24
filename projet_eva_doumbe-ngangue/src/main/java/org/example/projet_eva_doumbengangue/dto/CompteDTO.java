package org.example.projet_eva_doumbengangue.dto;

import org.example.projet_eva_doumbengangue.entity.Compte;

import java.time.LocalDate;

public record CompteDTO(Long id,
                        String numeroCompte,
                        Double solde,
                        LocalDate dateOuverture,
                        Compte.TypeCompte typeCompte,
                        Long clientId ) {
}
