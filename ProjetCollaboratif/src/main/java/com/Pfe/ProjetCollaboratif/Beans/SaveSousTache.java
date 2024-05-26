package com.Pfe.ProjetCollaboratif.Beans;

import com.Pfe.ProjetCollaboratif.Entity.SousTache;
import com.Pfe.ProjetCollaboratif.Entity.Tache;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

public class SaveSousTache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commentaire;
    private String description;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String fichier;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    private Set<String> checkList;
    private Long idTache;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<String> getCheckList() {
        return checkList;
    }

    public void setCheckList(Set<String> checkList) {
        this.checkList = checkList;
    }

    public Long getIdTache() {
        return idTache;
    }

    public void setIdTache(Long idTache) {
        this.idTache = idTache;
    }



    public static SousTache toEntity(SaveSousTache model)
    {
        if(model == null)
        {
            return null ;
        }
        SousTache tache=new SousTache();
        tache.setId(model.getId());
        tache.setDescription(model.getDescription());
        tache.setCommentaire(model.getCommentaire());
        tache.setFichier(model.getFichier());
        tache.setImage(model.getImage());
        tache.setCheckList(model.getCheckList());

        return tache;
    }
}
