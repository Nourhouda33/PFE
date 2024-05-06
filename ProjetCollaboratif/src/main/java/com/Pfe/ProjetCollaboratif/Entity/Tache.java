package com.Pfe.ProjetCollaboratif.Entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity

public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String discription;

    private String status;
    @ManyToOne
    Developpeurs developpeurs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getDiscription() {
        return discription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Developpeurs getDeveloppeurs() {
        return developpeurs;
    }

    public void setDeveloppeurs(Developpeurs developpeurs) {
        this.developpeurs = developpeurs;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }


}
