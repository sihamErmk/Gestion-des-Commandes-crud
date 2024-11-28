package org.example.atelier1.dao;

import org.example.atelier1.entities.Produit;

import java.sql.SQLException;
import java.util.List;

public interface ProduitRepository {
    Produit trouverById(Long id);
    void ajouterProduit(Produit produit);
    void modifierProduit(Produit produit);
    void supprimerProduit(Long id);
    List<Produit> trouverTous();
}