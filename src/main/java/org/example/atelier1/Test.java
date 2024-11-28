package org.example.atelier1;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test {
    public static void main(String[] args) {
        // Create EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myentity");

        // Create EntityManager
        EntityManager em = emf.createEntityManager();

        try {
            // Begin transaction
            em.getTransaction().begin();

            // Your JPA operations go here (e.g., persist, find, query, etc.)
            System.out.println("Transaction started successfully!");

            // Commit transaction
            em.getTransaction().commit();
        } catch (Exception e) {
            // Rollback transaction in case of error
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Close EntityManager and EntityManagerFactory
            if (em.isOpen()) {
                em.close();
            }
            if (emf.isOpen()) {
                emf.close();
            }
        }
    }
}