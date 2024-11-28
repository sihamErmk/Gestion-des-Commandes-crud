package org.example.atelier1.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.atelier1.dao.ClientRepository;
import org.example.atelier1.entities.Client;


import java.util.List;
@ApplicationScoped
public class ClientServiceImpl implements ClientService {

    @Inject
    private ClientRepository clientRepository;

    @Override
    public Client getClientById(Long id) {
        return clientRepository.trouverById(id);
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.ajouterClient(client);
        System.out.println("Client saved successfully");
    }

    @Override
    public void updateClient(Client client) {
        clientRepository.modifierClient(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.supprimerClient(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.trouverTous();
    }
}
