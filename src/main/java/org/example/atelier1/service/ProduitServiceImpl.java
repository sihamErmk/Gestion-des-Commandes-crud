package org.example.atelier1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.atelier1.dao.ProduitDAO;
import org.example.atelier1.dao.ProduitRepository;
import org.example.atelier1.entities.Produit;

import java.util.List;

@ApplicationScoped
@Transactional
public class ProduitServiceImpl implements ProduitService {

    @Inject
    private ProduitRepository produitRepository;

    @Override
    public Produit getProduitById(Long id) {
        return produitRepository.trouverById(id);
    }

    @Override
    public void saveProduit(Produit produit) {
        produitRepository.ajouterProduit(produit);
    }

    @Override
    public void updateProduit(Produit produit) {
        produitRepository.modifierProduit(produit);
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepository.supprimerProduit(id);
    }

    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.trouverTous();
    }
}