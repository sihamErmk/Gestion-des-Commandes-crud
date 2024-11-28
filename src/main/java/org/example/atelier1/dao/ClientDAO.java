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


import java.util.List;




@ApplicationScoped
@Transactional
public class ClientDAO implements ClientRepository {

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
    public Client trouverById(Long id) {
        return entityManager.find(Client.class, id);
    }

    @Override
    @Transactional
    public void ajouterClient(Client client) {
        System.out.println("Persisting client: " + client);
        entityManager.getTransaction().begin();

        entityManager.persist(client);
        entityManager.getTransaction().commit();
        System.out.println("Client persisted successfully");
    }

    @Override
    public void modifierClient(Client client) {
        entityManager.merge(client);
    }

    @Override
    public void supprimerClient(Long id) {
        Client client = trouverById(id);
        if (client != null) {
            entityManager.getTransaction().begin(); // Begin transaction
            entityManager.remove(client);
            entityManager.getTransaction().commit(); // Commit transaction
        }
    }

    @Override
    public List<Client> trouverTous() {
        return entityManager.createQuery("SELECT c FROM Client c", Client.class).getResultList();
    }
}