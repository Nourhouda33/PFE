package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Beans.SaveTacheDeveloppeur;
import com.Pfe.ProjetCollaboratif.Entity.Tache;

import java.util.List;
import java.util.Optional;

public interface TacheService {
    Tache ajouterTache(SaveTacheDeveloppeur module);
    Tache modifierTache(Tache tache);
    List<Tache> getTache();
    void supprimerTache(Long id);
    Optional<Tache> getTache(Long id);
}
