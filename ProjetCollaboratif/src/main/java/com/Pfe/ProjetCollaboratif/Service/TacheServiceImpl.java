package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Beans.SaveProjetEquipe;
import com.Pfe.ProjetCollaboratif.Beans.SaveTacheDeveloppeur;
import com.Pfe.ProjetCollaboratif.Entity.*;
import com.Pfe.ProjetCollaboratif.Repository.DeveloppeursRepository;
import com.Pfe.ProjetCollaboratif.Repository.ProjetRepository;
import com.Pfe.ProjetCollaboratif.Repository.SousTacheRepository;
import com.Pfe.ProjetCollaboratif.Repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
@Service
public class TacheServiceImpl implements TacheService{
    @Autowired
    ProjetRepository projetRepository;

    @Autowired
    TacheRepository tacheRepository;
    @Override
    public Tache ajouterTache(SaveTacheDeveloppeur module) {

        Tache tache= SaveTacheDeveloppeur.toEntity(module);
        /*System.out.println("idSousTâche"+module.getIdSousTache());
        SousTache St= sousTacheRepository.findById(module.getIdSousTache()).get();
        tache.setSousTache(St);*/
         System.out.println("idProjet"+module.getIdProjet());
        Projet proj=projetRepository.findById(module.getIdProjet()).get();
        tache.setProjet(proj);
        return tacheRepository.save(tache);
    }

    @Override
    public Tache modifierTache(Tache tache) {

        return tacheRepository.save(tache);
    }

    @Override
    public List<Tache> getTache() {
        return tacheRepository.findAll();
    }

    @Override
    public void supprimerTache(Long id) {
        tacheRepository.deleteById(id);

    }

    @Override
    public Optional<Tache> getTache(Long id) {
        return tacheRepository.findById(id);
    }


}
