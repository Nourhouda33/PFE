package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Entity.*;
import com.Pfe.ProjetCollaboratif.Repository.ConfirmationTokenRepository;
import com.Pfe.ProjetCollaboratif.Repository.DeveloppeursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloppeursServiceImpl implements DeveloppeursService {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    EmailServiceD emailServiceD;
    @Autowired
    DeveloppeursRepository developpeursRepository;
    @Autowired
    ConfirmationTokenRepository confirmationToRepository;
    @Override
    public ResponseEntity<Object> ajouterEquipe(Developpeurs developpeurs) {
        Developpeurs existingUser = developpeursRepository.findByEmail(developpeurs.getEmail());
        if (existingUser!=null) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        developpeurs.setPswd(this.bCryptPasswordEncoder.encode(developpeurs.getPswd()));
        developpeursRepository.save(developpeurs);
        ConfirmationToken confirmationToken = new ConfirmationToken(developpeurs);

        confirmationToRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(developpeurs.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8081/api/Developpeurs/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailServiceD.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");









    }
    @Override
    public ResponseEntity<?> ConfirmeEmail(String ConfirmationTocken){
    ConfirmationToken token = confirmationToRepository.findByConfirmationToken(ConfirmationTocken);

    if(token != null)
    {

        Developpeurs developpeur = developpeursRepository.findByEmail(token.getDeveloppeurs().getEmail());
        System.out.println("email from token " +token.getDeveloppeurs().getEmail());
        developpeur.setEtat(true);
        developpeursRepository.save(developpeur);
        return ResponseEntity.ok("Email verified successfully! "+"http://localhost:4200/login");
    }else {

        return ResponseEntity.badRequest().body("Error: Couldn't verify email");}
}
    @Override
    public Developpeurs modifierEquipe(Developpeurs developpeurs, long id) {
        return developpeursRepository.save(developpeurs);
    }

    @Override
    public List<Developpeurs> getEquipe() {
        return developpeursRepository.findAll();
    }

    @Override
    public void supprimerEquipe(Long id) {
        developpeursRepository.deleteById(id);

    }

    @Override
    public Optional<Developpeurs> getEquipeById(Long id) {
        return developpeursRepository.findById(id);
    }
}
