package org.example.projet_eva_doumbengangue.controller;

import org.example.projet_eva_doumbengangue.dto.CompteDTO;
import org.example.projet_eva_doumbengangue.entity.Compte;
import org.example.projet_eva_doumbengangue.mapper.CompteMapper;
import org.example.projet_eva_doumbengangue.service.CompteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    private final CompteService compteService;
    private final CompteMapper compteMapper;

    public CompteController(CompteService compteService,
                            CompteMapper compteMapper) {
        this.compteService = compteService;
        this.compteMapper = compteMapper;
    }

    // GET /api/comptes
    @GetMapping
    public ResponseEntity<List<CompteDTO>> getAllComptes() {
        List<Compte> comptes = compteService.getAllComptes();
        List<CompteDTO> dtos = comptes.stream()
                .map(compteMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET /api/comptes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CompteDTO> getCompteById(@PathVariable Long id) {
        Compte compte = compteService.getCompteById(id);
        return ResponseEntity.ok(compteMapper.toDTO(compte));
    }

    // POST /api/comptes
    @PostMapping
    public ResponseEntity<CompteDTO> createCompte(@RequestBody CompteDTO dto) {
        Compte compte = compteMapper.toEntity(dto);
        Compte created = compteService.createCompte(dto.clientId(), compte);
        return ResponseEntity.ok(compteMapper.toDTO(created));
    }

    // PUT /api/comptes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CompteDTO> updateCompte(@PathVariable Long id,
                                                  @RequestBody CompteDTO dto) {
        Compte toUpdate = compteMapper.toEntity(dto);
        Compte updated = compteService.updateCompte(id, toUpdate);
        return ResponseEntity.ok(compteMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        compteService.deleteCompte(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/comptes")
    public List<CompteDTO> getComptesByClient(@PathVariable Long id) {
        List<Compte> comptes = compteService.getComptesByClient(id);
        return comptes.stream()
                .map(compteMapper::toDTO)
                .toList();
    }

}
