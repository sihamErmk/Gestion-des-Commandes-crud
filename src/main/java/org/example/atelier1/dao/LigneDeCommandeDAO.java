package org.example.atelier1.dao;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.atelier1.entities.LigneDeCommande;

import java.util.List;
@ApplicationScoped
@Transactional
public class LigneDeCommandeDAO implements LigneDeCommandeRepository {

    @Inject
    private EntityManagerFactory entityManagerFactory; // Inject EntityManagerFactory

    private EntityManager entityManager; // Local EntityManager

    @PostConstruct
    public void init() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @PreDestroy
    public void close() {
        if (this.entityManager != null && this.entityManager.isOpen()) {
            this.entityManager.close();
        }
    }

    @Override
    public LigneDeCommande trouverById(Long id) {
        return entityManager.find(LigneDeCommande.class, id);
    }

    @Override
    public void ajouterLigneDeCommande(LigneDeCommande ligneDeCommande) {
        entityManager.getTransaction().begin();
        entityManager.persist(ligneDeCommande);
        entityManager.getTransaction().commit();
    }

    @Override
    public void modifierLigneDeCommande(LigneDeCommande ligneDeCommande) {
        entityManager.merge(ligneDeCommande);
    }

    @Override
    public void supprimerLigneDeCommande(Long id) {
        LigneDeCommande ligneDeCommande = trouverById(id);
        if (ligneDeCommande != null) {
            entityManager.getTransaction().begin(); // Begin transaction
            entityManager.remove(ligneDeCommande);
            entityManager.getTransaction().commit(); // Commit transaction
        }
    }

    @Override
    public List<LigneDeCommande> trouverTous() {
        return entityManager.createQuery("SELECT l FROM LigneDeCommande l", LigneDeCommande.class).getResultList();
    }
}
