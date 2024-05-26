package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Beans.SaveSousTache;
import com.Pfe.ProjetCollaboratif.Entity.SousTache;

import java.util.List;
import java.util.Optional;

public interface SousTacheService {
    SousTache ajouterSousTache(SaveSousTache sousTache);
    SousTache modifierSousTache(SousTache sousTache);
    List<SousTache> getSousTache();
    void supprimerSousTache(Long id);
    Optional<SousTache> getSousTache(Long id);
}
