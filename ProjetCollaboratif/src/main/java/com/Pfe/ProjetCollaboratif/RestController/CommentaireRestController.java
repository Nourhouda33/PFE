package com.Pfe.ProjetCollaboratif.RestController;

import com.Pfe.ProjetCollaboratif.Entity.Commentaire;
import com.Pfe.ProjetCollaboratif.Repository.CommentaireRepository;
import com.Pfe.ProjetCollaboratif.Service.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/commentaire")

public class CommentaireRestController {
    @Autowired
    CommentaireRepository commentaireRepository;
    @Autowired
    CommentaireService commentaireService;
    @RequestMapping(method = RequestMethod.POST)
    public  Commentaire AjouterCommentaire (@RequestBody Commentaire commentaire){
         return commentaireService.ajouterCommentaire(commentaire);

}
    @RequestMapping(method = RequestMethod.GET)
    public List<Commentaire> AfficherCommentaire(){
        return commentaireService.getCommentaire();
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Optional<Commentaire> AfichierCommentaierById(@PathVariable("id") Long id){
        Optional<Commentaire> comnt =commentaireService.getCommentaire(id);
        return comnt;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Commentaire ModifierCommentaire(@PathVariable("id") Long id,@RequestBody Commentaire commentaire){
        Commentaire nn= commentaireService.modifierCommentaire(commentaire);
        return nn;
    }
    @RequestMapping(value = ("/{id}") , method = RequestMethod.DELETE)
    public void SupprimerCommentaire(@PathVariable("id") Long id){
        commentaireService.supprimerCommentaire(id);
    }






}
