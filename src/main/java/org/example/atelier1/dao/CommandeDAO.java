package org.example.atelier1.dao;




import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.atelier1.entities.Client;
import org.example.atelier1.entities.Commande;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
@Transactional
public class CommandeDAO implements CommandeRepository {

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
    public Commande trouverById(Long id) {
        return entityManager.find(Commande.class, id);
    }

    @Override
    public void ajouterCommande(Commande commande) {
        entityManager.getTransaction().begin(); // Begin transaction
        entityManager.persist(commande);
        entityManager.getTransaction().commit(); // Commit transaction
    }

    @Override
    public void modifierCommande(Commande commande) {
        entityManager.merge(commande);
    }

    @Override
    public void supprimerCommande(Long id) {
        Commande commande = trouverById(id);
        if (commande != null) {
            entityManager.getTransaction().begin(); // Begin transaction
            entityManager.remove(commande);
            entityManager.getTransaction().commit(); // Commit transaction
        }
    }

    @Override
    public List<Commande> trouverTous() {
        return entityManager.createQuery("SELECT c FROM Commande c", Commande.class).getResultList();
    }
}