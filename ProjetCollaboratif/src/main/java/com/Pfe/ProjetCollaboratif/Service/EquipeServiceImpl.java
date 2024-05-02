package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Beans.SaveDeveloppeurEquipe;
import com.Pfe.ProjetCollaboratif.Beans.SaveTacheDeveloppeur;
import com.Pfe.ProjetCollaboratif.Entity.Developpeurs;
import com.Pfe.ProjetCollaboratif.Entity.Equipe;
import com.Pfe.ProjetCollaboratif.Entity.Tache;
import com.Pfe.ProjetCollaboratif.Repository.DeveloppeursRepository;
import com.Pfe.ProjetCollaboratif.Repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EquipeServiceImpl implements EquipeService {
    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    DeveloppeursRepository developpeursRepository;
    @Override
    public Equipe ajouterEquipe(SaveDeveloppeurEquipe module) {
        Equipe eq = SaveDeveloppeurEquipe.toEntity(module);
        System.out.println("idDeveloppeur" + module.getIdDeveloppeurs());
        Developpeurs dev = developpeursRepository.findById(module.getIdDeveloppeurs()).get();
        eq.setDeveloppeurs(dev);
        return equipeRepository.save(eq);
    }

    @Override
    public Equipe modifierEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    @Override
    public List<Equipe> getEquipe() {
        return equipeRepository.findAll();
    }

    @Override
    public void DeleteEquipe(Long id) {
        equipeRepository.deleteById(id);
    }

    @Override
    public Optional<Equipe> getEquipeById(Long id) {
        return equipeRepository.findById(id);
    }
}
