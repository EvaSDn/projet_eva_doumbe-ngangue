package org.example.projet_eva_doumbengangue.entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class CompteCourant extends Compte {
    private Double decouvertAutorise = 1000.0; // Par défaut 1000 euros
    public CompteCourant() {
    }

    public CompteCourant(String numeroCompte, Double solde, LocalDate dateOuverture) {
        super(numeroCompte, solde, dateOuverture);
        this.decouvertAutorise = 1000.0;
    }

    public CompteCourant(String numeroCompte, Double solde, LocalDate dateOuverture,
                         Double decouvertAutorise) {
        super(numeroCompte, solde, dateOuverture);
        this.decouvertAutorise = decouvertAutorise;
    }
    public Double getDecouvertAutorise() {
        return decouvertAutorise;
    }

    public void setDecouvertAutorise(Double decouvertAutorise) {
        this.decouvertAutorise = decouvertAutorise;
    }
}
