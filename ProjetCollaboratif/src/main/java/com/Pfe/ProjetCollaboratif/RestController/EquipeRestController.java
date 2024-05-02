package com.Pfe.ProjetCollaboratif.RestController;


import com.Pfe.ProjetCollaboratif.Beans.SaveDeveloppeurEquipe;
import com.Pfe.ProjetCollaboratif.Entity.Equipe;

import com.Pfe.ProjetCollaboratif.Repository.EquipeRepository;

import com.Pfe.ProjetCollaboratif.Service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/equipe")
@CrossOrigin("*")
@RestController
public class EquipeRestController {



    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    EquipeService equipeService;
    @RequestMapping(method = RequestMethod.POST)
    public Equipe AjouterEquipe(@RequestBody SaveDeveloppeurEquipe model){
        return equipeService.ajouterEquipe(model);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Equipe> AfficherEquipe(){
        return  equipeService.getEquipe();
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Optional<Equipe> AfichierEquipeById(@PathVariable("id") Long id){
        Optional<Equipe> proj =equipeService.getEquipeById(id);
        return proj;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Equipe ModifierEquipe(@PathVariable("id") Long id,@RequestBody Equipe equipe){
        Equipe newproj= equipeService.modifierEquipe(equipe);
        return newproj;
    }
    @RequestMapping(value = ("/{id}") , method = RequestMethod.DELETE)
    public void SupprimerEquipe(@PathVariable("id") Long id){
        equipeService.DeleteEquipe(id);
    }
}
