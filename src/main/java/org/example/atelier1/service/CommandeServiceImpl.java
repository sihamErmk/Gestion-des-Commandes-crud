package org.example.atelier1.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.atelier1.dao.CommandeDAO;
import org.example.atelier1.dao.CommandeRepository;
import org.example.atelier1.entities.Commande;

import java.util.List;

@ApplicationScoped
@Transactional
public class CommandeServiceImpl implements CommandeService {

    @Inject
    private CommandeRepository commandeRepository;

    @Override
    public Commande getCommandeById(Long id) {
        return commandeRepository.trouverById(id);
    }

    @Override
    public void saveCommande(Commande commande) {
        commandeRepository.ajouterCommande(commande);
    }

    @Override
    public void updateCommande(Commande commande) {
        commandeRepository.modifierCommande(commande);
    }

    @Override
    public void deleteCommande(Long id) {
        commandeRepository.supprimerCommande(id);
    }

    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.trouverTous();
    }
}
