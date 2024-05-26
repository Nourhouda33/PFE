package com.Pfe.ProjetCollaboratif.RestController;

import com.Pfe.ProjetCollaboratif.Beans.SaveTacheDeveloppeur;
import com.Pfe.ProjetCollaboratif.Entity.Tache;
import com.Pfe.ProjetCollaboratif.Repository.TacheRepository;
import com.Pfe.ProjetCollaboratif.Service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/Tache")
@CrossOrigin("*")
@RestController

public class TacheRestController {
    @Autowired
    TacheRepository tacheRepository;
    @Autowired
    TacheService tacheService;
    @RequestMapping(method = RequestMethod.POST)
    public Tache ajouterTache(@RequestBody SaveTacheDeveloppeur tache) {
      return tacheService.ajouterTache(tache);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Tache> AfficherTache(){
        return  tacheService.getTache();
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Optional<Tache> AfichierTacheById(@PathVariable("id") Long id){
        Optional<Tache> tach =tacheService.getTache(id);
        return tach;
    }






    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Tache ModifierTache(@PathVariable("id") Long id,@RequestBody Tache model){
        Tache newtach= tacheService.modifierTache(model);
        return newtach;
    }
    @RequestMapping(value = ("/{id}") , method = RequestMethod.DELETE)
    public void SupprimerTache(@PathVariable("id") Long id){
        tacheService.supprimerTache(id);
    }
}
