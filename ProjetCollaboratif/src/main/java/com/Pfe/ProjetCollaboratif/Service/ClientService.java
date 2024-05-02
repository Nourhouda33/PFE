package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client ajouterClient(Client client);
    Client modifierClient(Client client);
    List<Client> getClient();
    void supprimerClient( Long id);
    Optional<Client> getClientById(Long id);

}
