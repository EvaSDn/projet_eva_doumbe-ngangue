package org.example.projet_eva_doumbengangue.respository;

import org.example.projet_eva_doumbengangue.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
//public interface CompteRepository extends JpaRepository<Compte, Long> {

    Compte findByNumeroCompte(String numeroCompte);
    List<Compte> findByClientId(Long clientId);
    void deleteByClientId(Long clientId);

}
