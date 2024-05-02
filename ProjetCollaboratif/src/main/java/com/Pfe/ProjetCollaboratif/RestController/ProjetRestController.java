package com.Pfe.ProjetCollaboratif.RestController;

import com.Pfe.ProjetCollaboratif.Beans.SaveProjetEquipe;
import com.Pfe.ProjetCollaboratif.Entity.Projet;
import com.Pfe.ProjetCollaboratif.Repository.ProjetRepository;
import com.Pfe.ProjetCollaboratif.Service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/Projet")
public class ProjetRestController {
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    ProjetService projetService;
    @RequestMapping(method = RequestMethod.POST)
    public Projet AjouterProjet(@RequestBody SaveProjetEquipe model){
        return projetService.ajouterProjet(model);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Projet> AfficherProjet(){
        return  projetService.getProjet();
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Optional<Projet> AfichierProjetById(@PathVariable("id") Long id){
        Optional<Projet> proj =projetService.getProjet(id);
        return proj;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Projet ModifierProjet(@PathVariable("id") Long id,@RequestBody Projet projet){
       Projet newproj= projetService.modifierProjet(projet);
        return newproj;
    }
    @RequestMapping(value = ("/{id}") , method = RequestMethod.DELETE)
    public void SupprimerProjet(@PathVariable("id") Long id){
        projetService.supprimerFichier(id);
    }
}
