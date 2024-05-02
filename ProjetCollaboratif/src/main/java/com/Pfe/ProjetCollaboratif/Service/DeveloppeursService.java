package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Entity.Developpeurs;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DeveloppeursService {
    ResponseEntity<Object> ajouterEquipe(Developpeurs developpeurs);
    ResponseEntity<?> ConfirmeEmail (String ConfermationTocken);
    Developpeurs modifierEquipe(Developpeurs developpeurs, long id);
    List<Developpeurs> getEquipe();
    void supprimerEquipe(Long id);
    Optional<Developpeurs> getEquipeById(Long id);

}
