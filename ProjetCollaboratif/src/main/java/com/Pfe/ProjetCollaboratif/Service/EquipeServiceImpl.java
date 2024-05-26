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

import java.util.*;

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

        // Récupérer tous les identifiants de développeurs
        List<Long> idDeveloppeurs = module.getIdDeveloppeurs();

        // Créer une liste pour stocker les développeurs
        List<Developpeurs> devs = new ArrayList<>();

        // Boucler sur chaque identifiant de développeur pour les récupérer depuis la base de données
        for (Long id : idDeveloppeurs) {
            Optional<Developpeurs> optionalDev = developpeursRepository.findById(id);
            if (optionalDev.isPresent()) {
                devs.add(optionalDev.get());
            } else {
                // Gérer le cas où un développeur avec l'ID spécifié n'existe pas
                // Vous pouvez lever une exception ou gérer autrement cette situation
                System.out.println("Le développeur avec l'ID " + id + " n'existe pas.");
            }
        }

        // Ajouter les développeurs récupérés à l'équipe
        eq.setDeveloppeurs(new HashSet<>(devs));

        // Enregistrer l'équipe avec les développeurs associés
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
