package com.Pfe.ProjetCollaboratif.Beans;

import com.Pfe.ProjetCollaboratif.Entity.Projet;
import jakarta.persistence.*;

import java.util.Date;

public class SaveProjetEquipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String logo;
    private String nom;
    private Date dateDebuit;
    private Date dateFin;
    private String discription;

    private String status;
    private Long idEquipe;


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

    public Date getDateDebuit() {
        return dateDebuit;
    }

    public void setDateDebuit(Date dateDebuit) {
        this.dateDebuit = dateDebuit;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }



    public static Projet toEntity(SaveProjetEquipe model)
    {
        if(model == null)
        {
            return null ;
        }
        Projet projet=new Projet();
        projet.setId(model.getId());
        projet.setNom(model.getNom());
        projet.setDateDebuit(model.getDateDebuit());
        projet.setDateFin(model.getDateFin());
        projet.setDiscription(model.getDiscription());
        projet.setLogo(model.getLogo());
        projet.setStatus(model.getStatus());

        return projet;
    }
}
