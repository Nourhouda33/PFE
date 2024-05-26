package com.Pfe.ProjetCollaboratif.RestController;

import com.Pfe.ProjetCollaboratif.Beans.SaveSousTache;
import com.Pfe.ProjetCollaboratif.Entity.SousTache;
import com.Pfe.ProjetCollaboratif.Entity.Tache;
import com.Pfe.ProjetCollaboratif.Repository.SousTacheRepository;
import com.Pfe.ProjetCollaboratif.Service.SousTacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/SousTache")
@CrossOrigin("*")
@RestController
public class SousTacheRestController {
    @Autowired
    SousTacheRepository sousTacheRepository;
    @Autowired
    SousTacheService sousTacheService;


    @RequestMapping(method = RequestMethod.POST)
    public SousTache ajouterSousTache(@RequestBody SaveSousTache sousTache) {
        return sousTacheService.ajouterSousTache(sousTache);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<SousTache> AfficherSousTache(){
        return  sousTacheService.getSousTache();
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Optional<SousTache> AfichierSousTacheById(@PathVariable("id") Long id){
        Optional<SousTache> tach =sousTacheService.getSousTache(id);
        return tach;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public SousTache ModifierSousTache(@PathVariable("id") Long id,@RequestBody SousTache model){
        SousTache newtach= sousTacheService.modifierSousTache(model);
        return newtach;
    }
    @RequestMapping(value = ("/{id}") , method = RequestMethod.DELETE)
    public void SupprimerSousTache(@PathVariable("id") Long id){
        sousTacheService.supprimerSousTache(id);
    }
}
