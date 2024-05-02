package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Entity.Client;
import com.Pfe.ProjetCollaboratif.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    ClientRepository clientRepository;
    @Override
    public Client ajouterClient(Client client) {

        return clientRepository.save(client);
    }

    @Override
    public Client modifierClient(Client client) {

        return clientRepository.save(client);
    }

    @Override
    public List<Client> getClient() {

        return clientRepository.findAll();
    }

    @Override
    public void supprimerClient(Long id) {
        clientRepository.deleteById(id);

    }

    @Override
    public Optional<Client> getClientById(Long id) {

        return clientRepository.findById(id);
    }
}
