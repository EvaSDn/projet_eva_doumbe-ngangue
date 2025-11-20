package org.example.projet_eva_doumbengangue.respository;

import org.example.projet_eva_doumbengangue.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClientRespository extends JpaRepository<Client, Long> {
List<Client> findByNames(String prenom, String nom);

}
