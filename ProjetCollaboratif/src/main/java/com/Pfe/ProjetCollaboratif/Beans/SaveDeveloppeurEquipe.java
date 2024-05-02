package com.Pfe.ProjetCollaboratif.Beans;

import com.Pfe.ProjetCollaboratif.Entity.Equipe;
import com.Pfe.ProjetCollaboratif.Entity.Tache;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

public class SaveDeveloppeurEquipe {
    @Id
    @GeneratedValue()
    private Long id ;
    private String nom;
    private String discription;
    private Long idDeveloppeurs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Long getIdDeveloppeurs() {
        return idDeveloppeurs;
    }

    public void setIdDeveloppeurs(Long idDeveloppeurs) {
        this.idDeveloppeurs = idDeveloppeurs;
    }

    public static Equipe toEntity(SaveDeveloppeurEquipe model)
    {
        if(model == null)
        {
            return null ;
        }
        Equipe developpeurs=new Equipe();
        developpeurs.setId(model.getId());
        developpeurs.setNom(model.getNom());
        developpeurs.setDiscription(model.getDiscription());




        return developpeurs;
    }
}
