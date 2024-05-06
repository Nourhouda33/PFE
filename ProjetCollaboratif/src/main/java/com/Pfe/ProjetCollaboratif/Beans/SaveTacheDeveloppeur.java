package com.Pfe.ProjetCollaboratif.Beans;

import com.Pfe.ProjetCollaboratif.Entity.Tache;

public class SaveTacheDeveloppeur {
    private Long id;

    private String discription;

    private String status;
    private Long idDeveloppeurs;

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



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdDeveloppeurs() {
        return idDeveloppeurs;
    }

    public void setIdDeveloppeurs(Long idDeveloppeurs) {
        this.idDeveloppeurs = idDeveloppeurs;
    }

    public static Tache toEntity(SaveTacheDeveloppeur model)
    {
        if(model == null)
        {
            return null ;
        }
        Tache tache=new Tache();
        tache.setId(model.getId());
        tache.setDiscription(model.getDiscription());
        tache.setStatus(model.getStatus());
        return tache;
    }
}
