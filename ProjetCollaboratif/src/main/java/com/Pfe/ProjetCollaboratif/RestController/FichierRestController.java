package com.Pfe.ProjetCollaboratif.RestController;

import com.Pfe.ProjetCollaboratif.Entity.Fichier;
import com.Pfe.ProjetCollaboratif.Repository.FichierRepository;
import com.Pfe.ProjetCollaboratif.Service.FichierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/fichier")
public class FichierRestController {
    @Autowired
    FichierRepository fichierRepository;
    @Autowired
    FichierService fichierService;
    @RequestMapping(method = RequestMethod.POST)
    public Fichier AjouterFichier (@RequestBody Fichier fichier){
        return fichierService.ajouterFichier(fichier);

}
    @RequestMapping(method = RequestMethod.GET)
    public List<Fichier> AfficherFichier(){
        return fichierService.getFichier();
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Optional<Fichier> AfichierFichierById(@PathVariable("id") Long id){
        Optional<Fichier> fichie =fichierService.getFichier(id);
        return fichie;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Fichier ModifierFichier(@PathVariable("id") Long id,@RequestBody Fichier fichier){
        Fichier newfichie= fichierService.modifierFichier(fichier);
        return newfichie;
    }
    @RequestMapping(value = ("/{id}") , method = RequestMethod.DELETE)
    public void SupprimerFichier(@PathVariable("id") Long id){
        fichierService.supprimerFichier(id);
    }
}
