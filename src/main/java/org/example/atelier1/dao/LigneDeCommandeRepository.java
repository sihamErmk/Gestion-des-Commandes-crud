package org.example.atelier1.dao;

import org.example.atelier1.entities.LigneDeCommande;

import java.util.List;

public interface LigneDeCommandeRepository {
    LigneDeCommande trouverById(Long id);
    void ajouterLigneDeCommande(LigneDeCommande ligneDeCommande);
    void modifierLigneDeCommande(LigneDeCommande ligneDeCommande);
    void supprimerLigneDeCommande(Long id);
    List<LigneDeCommande> trouverTous();
}