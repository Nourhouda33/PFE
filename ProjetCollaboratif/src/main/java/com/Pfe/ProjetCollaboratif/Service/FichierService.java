package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Entity.Commentaire;
import com.Pfe.ProjetCollaboratif.Entity.Fichier;

import java.util.List;
import java.util.Optional;

public interface FichierService {
    Fichier ajouterFichier(Fichier fichier);
    Fichier modifierFichier(Fichier fichier);
    List<Fichier> getFichier();
    void supprimerFichier(Long id);
    Optional<Fichier> getFichier(Long id);

}
