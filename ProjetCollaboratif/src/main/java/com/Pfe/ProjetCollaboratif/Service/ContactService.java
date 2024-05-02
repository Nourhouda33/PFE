package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Entity.Contact;
import com.Pfe.ProjetCollaboratif.Entity.Fichier;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Contact ajouterContact(Contact contact);
    Contact modifierContact(Contact contact);
    List<Contact> getContact();
    void supprimerContact(Long id);
    Optional<Contact> getContact(Long id);
}
