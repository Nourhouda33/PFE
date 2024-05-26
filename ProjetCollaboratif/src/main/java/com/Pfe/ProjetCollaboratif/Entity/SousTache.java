package com.Pfe.ProjetCollaboratif.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
public class SousTache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

   private String commentaire;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
   private String fichier;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
   private String image;
   private Set<String> checkList = new HashSet<>();
@ManyToOne
Tache tache;

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Set<String> getCheckList() {
        return checkList;
    }

    public void setCheckList(Set<String> checkList) {
        this.checkList = checkList;
    }
}
