package org.example.atelier1.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.atelier1.dao.LigneDeCommandeRepository;
import org.example.atelier1.entities.LigneDeCommande;

import java.util.List;

@ApplicationScoped
@Transactional
public class LigneDeCommandeServiceImpl implements LigneDeCommandeService {

    @Inject
    private LigneDeCommandeRepository ligneDeCommandeRepository;

    @Override
    public LigneDeCommande getLigneDeCommandeById(Long id) {
        return ligneDeCommandeRepository.trouverById(id);
    }

    @Override
    public void saveLigneDeCommande(LigneDeCommande ligneDeCommande) {
        ligneDeCommandeRepository.ajouterLigneDeCommande(ligneDeCommande);
    }

    @Override
    public void updateLigneDeCommande(LigneDeCommande ligneDeCommande) {
        ligneDeCommandeRepository.modifierLigneDeCommande(ligneDeCommande);
    }

    @Override
    public void deleteLigneDeCommande(Long id) {
        ligneDeCommandeRepository.supprimerLigneDeCommande(id);
    }

    @Override
    public List<LigneDeCommande> getAllLignesDeCommande() {
        return ligneDeCommandeRepository.trouverTous();
    }
}