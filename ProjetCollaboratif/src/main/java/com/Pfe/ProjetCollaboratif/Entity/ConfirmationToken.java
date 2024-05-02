package com.Pfe.ProjetCollaboratif.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private Long tokenId;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
//un relation un a un etre confermationtoken et deve
    @OneToOne(targetEntity = Developpeurs.class, fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
  //la jointure entre la dev et confermation tocken
    @JoinColumn(nullable = false, name = "developpeur_id")
    private Developpeurs developpeurs;



    public ConfirmationToken(Developpeurs developpeurs) {
        this.developpeurs = developpeurs;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Developpeurs getDeveloppeurs() {
        return developpeurs;
    }

    public void setDeveloppeurs(Developpeurs developpeurs) {
        this.developpeurs = developpeurs;
    }
}
