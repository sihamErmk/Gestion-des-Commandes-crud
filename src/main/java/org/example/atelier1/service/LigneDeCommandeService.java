package org.example.atelier1.service;

import org.example.atelier1.entities.LigneDeCommande;

import java.util.List;

public interface LigneDeCommandeService {
    LigneDeCommande getLigneDeCommandeById(Long id);
    void saveLigneDeCommande(LigneDeCommande ligneDeCommande);
    void updateLigneDeCommande(LigneDeCommande ligneDeCommande);
    void deleteLigneDeCommande(Long id);
    List<LigneDeCommande> getAllLignesDeCommande();
}
