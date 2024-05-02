package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Beans.SaveTacheDeveloppeur;
import com.Pfe.ProjetCollaboratif.Entity.Developpeurs;
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
    public Tache ajouterTache(SaveTacheDeveloppeur module) {
        Tache tache = SaveTacheDeveloppeur.toEntity(module);
        System.out.println("idDeveloppeur" + module.getIdDeveloppeur());
        Developpeurs dev = developpeursRepository.findById(module.getIdDeveloppeur()).get();
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
