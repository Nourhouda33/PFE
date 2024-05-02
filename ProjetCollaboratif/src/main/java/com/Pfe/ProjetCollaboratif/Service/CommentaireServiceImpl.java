package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Entity.Commentaire;
import com.Pfe.ProjetCollaboratif.Repository.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CommentaireServiceImpl implements CommentaireService {
    @Autowired
    CommentaireRepository commentaireRepository;
    @Override
    public Commentaire ajouterCommentaire(Commentaire commentaire) {

        return commentaireRepository.save(commentaire);
    }

    @Override
    public Commentaire modifierCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);

    }

    @Override
    public List<Commentaire> getCommentaire() {
        return commentaireRepository.findAll();
    }

    @Override
    public void supprimerCommentaire(Long id) {
        commentaireRepository.deleteById(id);

    }

    @Override
    public Optional<Commentaire> getCommentaire(Long id) {
        return commentaireRepository.findById(id);
    }
}
