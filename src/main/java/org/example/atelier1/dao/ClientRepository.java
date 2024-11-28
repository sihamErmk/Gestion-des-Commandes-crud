package org.example.atelier1.dao;


import org.example.atelier1.entities.Client;

import java.util.List;

public interface ClientRepository {
        Client trouverById(Long id);
        void ajouterClient(Client client);
        void modifierClient(Client client);
        void supprimerClient(Long id);
        List<Client> trouverTous();
}