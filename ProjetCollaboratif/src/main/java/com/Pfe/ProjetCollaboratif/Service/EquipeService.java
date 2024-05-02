package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Beans.SaveDeveloppeurEquipe;
import com.Pfe.ProjetCollaboratif.Entity.Equipe;

import java.util.List;
import java.util.Optional;

public interface EquipeService {
    Equipe ajouterEquipe(SaveDeveloppeurEquipe modeule);
    Equipe modifierEquipe(Equipe equipe);
    List<Equipe> getEquipe();
    void DeleteEquipe(Long id);
    Optional<Equipe> getEquipeById(Long id);
}
