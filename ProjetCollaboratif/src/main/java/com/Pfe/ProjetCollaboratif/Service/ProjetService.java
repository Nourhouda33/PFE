package com.Pfe.ProjetCollaboratif.Service;


import com.Pfe.ProjetCollaboratif.Beans.SaveProjetEquipe;
import com.Pfe.ProjetCollaboratif.Entity.Projet;

import java.util.List;
import java.util.Optional;

public interface ProjetService {
    Projet ajouterProjet(SaveProjetEquipe model);
    Projet modifierProjet(Projet projet);
    List<Projet> getProjet();
    void supprimerFichier(Long id);
    Optional<Projet> getProjet(Long id);
}
