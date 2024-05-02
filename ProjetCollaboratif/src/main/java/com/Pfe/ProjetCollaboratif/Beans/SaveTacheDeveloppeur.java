package com.Pfe.ProjetCollaboratif.Beans;

import com.Pfe.ProjetCollaboratif.Entity.Tache;

public class SaveTacheDeveloppeur {
    private Long id;

    private String discription;
    private boolean done;
    private Long idDeveloppeur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Long getIdDeveloppeur() {
        return idDeveloppeur;
    }

    public void setIdDeveloppeur(Long idDeveloppeur) {
        this.idDeveloppeur = idDeveloppeur;
    }

    public static Tache toEntity(SaveTacheDeveloppeur model)
    {
        if(model == null)
        {
            return null ;
        }
        Tache developpeurs=new Tache();
        developpeurs.setId(model.getId());
        developpeurs.setDiscription(model.getDiscription());
        developpeurs.setDone(model.isDone());



        return developpeurs;
    }
}
