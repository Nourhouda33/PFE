package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Entity.Contact;
import com.Pfe.ProjetCollaboratif.Entity.Projet;
import com.Pfe.ProjetCollaboratif.Repository.ContactRepository;
import com.Pfe.ProjetCollaboratif.Repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact ajouterContact(Contact contact) {

        return contactRepository.save(contact);
    }

    @Override
    public Contact modifierContact(Contact contact) {

        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getContact() {
        return contactRepository.findAll();
    }

    @Override
    public void supprimerContact(Long id) {
        contactRepository.deleteById(id);

    }

    @Override
    public Optional<Contact> getContact(Long id) {
        return contactRepository.findById(id);
    }
}
