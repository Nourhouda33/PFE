package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Entity.Commentaire;

import java.util.List;
import java.util.Optional;

public interface CommentaireService {
    Commentaire ajouterCommentaire(Commentaire commentaire);
    Commentaire modifierCommentaire(Commentaire commentaire);
    List<Commentaire> getCommentaire();
    void supprimerCommentaire(Long id);
    Optional<Commentaire> getCommentaire(Long id);

}
