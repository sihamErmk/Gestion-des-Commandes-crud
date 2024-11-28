package org.example.atelier1.service;

import org.example.atelier1.entities.Commande;

import java.util.List;

public interface CommandeService {
    Commande getCommandeById(Long id);
    void saveCommande(Commande commande);
    void updateCommande(Commande commande);
    void deleteCommande(Long id);
    List<Commande> getAllCommandes();
}