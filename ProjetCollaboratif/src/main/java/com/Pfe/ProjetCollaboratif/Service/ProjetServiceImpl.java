package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Beans.SaveProjetEquipe;
import com.Pfe.ProjetCollaboratif.Entity.Equipe;
import com.Pfe.ProjetCollaboratif.Entity.Projet;
import com.Pfe.ProjetCollaboratif.Entity.Tache;
import com.Pfe.ProjetCollaboratif.Repository.EquipeRepository;
import com.Pfe.ProjetCollaboratif.Repository.ProjetRepository;
import com.Pfe.ProjetCollaboratif.Repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetServiceImpl implements ProjetService{
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    TacheRepository tacheRepository;
    @Autowired
    EquipeRepository equipeRepository;
    @Override
    public Projet ajouterProjet(SaveProjetEquipe model) {

        Projet projet= SaveProjetEquipe.toEntity(model);
        System.out.println("idTache"+model.getIdTache());
        Tache tache=tacheRepository.findById(model.getIdTache()).get();
        projet.setTache(tache);
        System.out.println("IdEquipe"+model.getIdEquipe());
        Equipe equipe=equipeRepository.findById(model.getIdEquipe()).get();
        projet.setEquipe(equipe);
        return projetRepository.save(projet);
    }

    @Override
    public Projet modifierProjet(Projet projet) {

        return projetRepository.save(projet);
    }

    @Override
    public List<Projet> getProjet() {

        return projetRepository.findAll();
    }

    @Override
    public void supprimerFichier(Long id) {
        projetRepository.deleteById(id);

    }

    @Override
    public Optional<Projet> getProjet(Long id) {

        return projetRepository.findById(id);
    }
}
