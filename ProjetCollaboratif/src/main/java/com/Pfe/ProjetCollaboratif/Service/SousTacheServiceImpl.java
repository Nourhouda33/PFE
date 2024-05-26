package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Beans.SaveSousTache;
import com.Pfe.ProjetCollaboratif.Beans.SaveTacheDeveloppeur;
import com.Pfe.ProjetCollaboratif.Entity.SousTache;
import com.Pfe.ProjetCollaboratif.Entity.Tache;
import com.Pfe.ProjetCollaboratif.Repository.SousTacheRepository;
import com.Pfe.ProjetCollaboratif.Repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SousTacheServiceImpl implements SousTacheService {
    @Autowired
    TacheRepository tacheRepository;
    @Autowired
    SousTacheRepository sousTacheRepository;
    @Override
    public SousTache ajouterSousTache(SaveSousTache module) {
        SousTache sousTache= SaveSousTache.toEntity(module);
        System.out.println("idTâche"+module.getIdTache());
        Tache St= tacheRepository.findById(module.getIdTache()).get();
        sousTache.setTache(St);
        return sousTacheRepository.save(sousTache);
    }

    @Override
    public SousTache modifierSousTache(SousTache sousTache) {
        return sousTacheRepository.save(sousTache);
    }

    @Override
    public List<SousTache> getSousTache() {
        return sousTacheRepository.findAll();
    }

    @Override
    public void supprimerSousTache(Long id) {
           sousTacheRepository.deleteById(id);
    }

    @Override
    public Optional<SousTache> getSousTache(Long id) {
        return sousTacheRepository.findById(id);
    }
}
