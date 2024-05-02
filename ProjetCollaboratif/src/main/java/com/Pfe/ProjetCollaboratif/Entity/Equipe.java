package com.Pfe.ProjetCollaboratif.Entity;

import jakarta.persistence.*;

@Entity
public class Equipe {
    @Id
    @GeneratedValue()
    private Long id ;
    private String nom;

    private String discription;
    @ManyToOne
    Developpeurs developpeurs;

    public Developpeurs getDeveloppeurs() {
        return developpeurs;
    }

    public void setDeveloppeurs(Developpeurs developpeurs) {
        this.developpeurs = developpeurs;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

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


}
