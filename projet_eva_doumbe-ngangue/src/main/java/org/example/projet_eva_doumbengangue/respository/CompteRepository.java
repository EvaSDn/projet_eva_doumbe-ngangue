package org.example.projet_eva_doumbengangue.respository;

import org.example.projet_eva_doumbengangue.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
}
