package org.example.atelier1.dao;


import org.example.atelier1.entities.Commande;

import java.util.List;

public interface CommandeRepository {
    Commande trouverById(Long id);
    void ajouterCommande(Commande commande);
    void modifierCommande(Commande commande);
    void supprimerCommande(Long id);
    List<Commande> trouverTous();
}