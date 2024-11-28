package org.example.atelier1.dao;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.atelier1.entities.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class ProduitDAO implements ProduitRepository {

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
    public Produit trouverById(Long id) {
        return entityManager.find(Produit.class, id);
    }

    @Override
    public void ajouterProduit(Produit produit) {
        entityManager.getTransaction().begin();

        entityManager.persist(produit);
        entityManager.getTransaction().commit();

    }

    @Override
    public void modifierProduit(Produit produit) {
        entityManager.getTransaction().begin();
        entityManager.merge(produit);
        entityManager.getTransaction().commit();
    }

    @Override
    public void supprimerProduit(Long id) {
        Produit produit = trouverById(id);
        if (produit != null) {
            entityManager.getTransaction().begin(); // Begin transaction
            entityManager.remove(produit);
            entityManager.getTransaction().commit(); // Commit transaction
        }
    }

    @Override
    public List<Produit> trouverTous() {
        return entityManager.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
    }
}
