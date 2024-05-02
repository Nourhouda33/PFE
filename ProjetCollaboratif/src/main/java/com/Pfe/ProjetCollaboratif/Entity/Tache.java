package com.Pfe.ProjetCollaboratif.Entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity

public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String discription;
    private boolean done;
    @ManyToOne
    Developpeurs developpeurs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDiscription() {
        return discription;
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
