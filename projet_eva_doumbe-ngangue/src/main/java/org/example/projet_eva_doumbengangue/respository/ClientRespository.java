package org.example.projet_eva_doumbengangue.respository;

import org.example.projet_eva_doumbengangue.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//public interface ClientRespository extends JpaRepository<Client, Long> {
public interface ClientRespository extends JpaRepository<Client, Long> {
    List<Client> findByPrenomAndNom(String prenom, String nom);
}
