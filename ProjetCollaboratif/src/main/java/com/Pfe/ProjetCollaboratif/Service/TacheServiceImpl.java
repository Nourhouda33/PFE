package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Beans.SaveProjetEquipe;
import com.Pfe.ProjetCollaboratif.Beans.SaveTacheDeveloppeur;
import com.Pfe.ProjetCollaboratif.Entity.Developpeurs;
import com.Pfe.ProjetCollaboratif.Entity.Equipe;
import com.Pfe.ProjetCollaboratif.Entity.Projet;
import com.Pfe.ProjetCollaboratif.Entity.Tache;
import com.Pfe.ProjetCollaboratif.Repository.DeveloppeursRepository;
import com.Pfe.ProjetCollaboratif.Repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TacheServiceImpl implements TacheService{
    @Autowired
    DeveloppeursRepository developpeursRepository;
    @Autowired
    TacheRepository tacheRepository;
    @Override
    public Tache ajouterTache(SaveTacheDeveloppeur model) {

        Tache tache= SaveTacheDeveloppeur.toEntity(model);
        System.out.println("idDeveloppeur"+model.getIdDeveloppeurs());
        Developpeurs dev=developpeursRepository.findById(model.getIdDeveloppeurs()).get();
        tache.setDeveloppeurs(dev);
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
