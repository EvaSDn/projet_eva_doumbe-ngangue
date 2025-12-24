package org.example.projet_eva_doumbengangue.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Compte {


    public enum TypeCompte {
        COURANT,
        EPARGNE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCompte;
    private Double solde;
    private LocalDate dateOuverture;

    @Enumerated(EnumType.STRING)
    private TypeCompte typeCompte;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public Compte() {}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNumeroCompte() {
        return numeroCompte;
    }
    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }
    public Double getSolde() {
        return solde;
    }
    public void setSolde(Double solde) {
        this.solde = solde;
    }
    public LocalDate getDateOuverture() {
        return dateOuverture;
    }
    public void setDateOuverture(LocalDate dateOuverture) {
        this.dateOuverture = dateOuverture;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public TypeCompte getTypeCompte() { return typeCompte; }
    public void setTypeCompte(TypeCompte typeCompte) { this.typeCompte = typeCompte; }
}
