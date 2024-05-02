package com.Pfe.ProjetCollaboratif.RestController;

import com.Pfe.ProjetCollaboratif.Entity.Contact;
import com.Pfe.ProjetCollaboratif.Entity.Fichier;
import com.Pfe.ProjetCollaboratif.Repository.ContactRepository;
import com.Pfe.ProjetCollaboratif.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/Contact")
@RestController
@CrossOrigin("*")
public class ContactRestController {
    @Autowired
    ContactService contactService;
    @Autowired
    ContactRepository contactRepository;
    @RequestMapping (method = RequestMethod.POST)
    public Contact AjouterContact(@RequestBody Contact contact){
        return contactService.ajouterContact(contact);
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> AfficherContact(){
        return contactService.getContact();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Optional<Contact> AfichierContactById(@PathVariable("id") Long id){
        Optional<Contact> cont =contactService.getContact(id);
        return cont;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Contact ModifierContact(@PathVariable("id") Long id,@RequestBody Contact contact){
        Contact newcont= contactService.modifierContact(contact);
        return newcont;
    }
    @RequestMapping(value = ("/{id}") , method = RequestMethod.DELETE)
    public void SupprimerContact(@PathVariable("id") Long id){
        contactService.supprimerContact(id);
    }





}
