package com.Pfe.ProjetCollaboratif.RestController;

import com.Pfe.ProjetCollaboratif.Entity.Developpeurs;
import com.Pfe.ProjetCollaboratif.Repository.DeveloppeursRepository;
import com.Pfe.ProjetCollaboratif.Service.DeveloppeursService;
import com.Pfe.ProjetCollaboratif.Service.EmailService;
import com.Pfe.ProjetCollaboratif.Service.ResetMdpService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/Developpeurs")
public class DeveloppeursRestController {
    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    @Autowired
    DeveloppeursService developpeursService;
    @Autowired
    ResetMdpService resetMdpService;
    @Autowired
    DeveloppeursRepository developpeursRepository;
    @Autowired
    EmailService emailService;
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return developpeursService.ConfirmeEmail(confirmationToken);
    }
    @RequestMapping(value = "/forgotmdp", method = RequestMethod.POST)
    public ResponseEntity<?> forgotMdp(@RequestBody Developpeurs developpeurs) {
        System.out.println("Demande de réinitialisation de mot de passe reçue pour l'e-mail: " + developpeurs);
        Developpeurs userFromDB = developpeursRepository.findByEmail(developpeurs.getEmail());
        if (userFromDB == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        } else {
            String nvmdp = resetMdpService.nvMdp();
            userFromDB.setPswd(bCryptPasswordEncoder.encode(nvmdp));
            developpeursRepository.save(userFromDB);

            emailService.SendSimpleMessage(developpeurs.getEmail(), "Votre nouveau mot de passe", "Bonjour,\n" +
                    "Votre mot de passe sur CollabPro a été re-initlaisé, le nouveau mot de passe est : " + nvmdp);
            return ResponseEntity.status(HttpStatus.OK).body("Instructions de réinitialisation du mot de passe envoyées à votre adresse e-mail");
        }
    }
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> AjouterEquipe(@RequestBody Developpeurs developpeurs){
       return developpeursService.ajouterEquipe(developpeurs);
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Developpeurs> AfficherEquipe(){
        return developpeursService.getEquipe();
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Optional<Developpeurs> AfficherDeveloppeursById(@PathVariable("id") Long id){
        Optional<Developpeurs> eq=developpeursService.getEquipeById(id);
        return eq;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void SupprimerEquipe(@PathVariable("id") Long id){
        developpeursService.supprimerEquipe(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Developpeurs modifierEquipe(@RequestBody Developpeurs developpeurs, @PathVariable("id") Long id) {
        Developpeurs newDeveloppeurs = null;
        if (developpeursRepository.findById(id).isPresent()) { //ken user deja mawjoud
            Developpeurs dev1 = developpeursRepository.findById(id).get();
            var fouid = developpeurs.getId();
            var nom = developpeurs.getNom();
            var prenom = developpeurs.getPrenom();
            var email = developpeurs.getEmail();
            var pswd = developpeurs.getPswd();
            var adresse = developpeurs.getAdresse();
            var tel = developpeurs.getTel();

            dev1.setId(fouid);
            dev1.setNom(nom);
            dev1.setPrenom(prenom);
            dev1.setEmail(email);
            dev1.setPswd(pswd);
            dev1.setAdresse(adresse);
            dev1.setTel(tel);


//mta3 yjih mail fih l etat
            developpeurs.setPswd(this.bCryptPasswordEncoder.encode(dev1.getPswd()));
            if (developpeurs.isEtat() != dev1.isEtat()) {
                //ternary expression
                String etat = dev1.isEtat() ? "Bloqué" : "Accepté";
                emailService.SendSimpleMessage(dev1.getEmail(), "L'etat de votre compte", "votre compte a été " + etat);
            }
            dev1.setEtat(developpeurs.isEtat());
            newDeveloppeurs = developpeursRepository.save(dev1);
        }
        return newDeveloppeurs;
    }







    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> LoginEquipe(@RequestBody Developpeurs developpeurs){
        System.out.println("in login Developpeurs" + developpeurs);
        HashMap<String,Object> response=new HashMap<>();
        Developpeurs userFrombd= developpeursRepository.findByEmail(developpeurs.getEmail());
        System.out.println("user from db"+userFrombd );
        if(userFrombd==null){
            response.put("message","Developpeurs not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            boolean compare=this.bCryptPasswordEncoder.matches(developpeurs.getPswd(),userFrombd.getPswd());
            System.out.println("compare"+compare);
            if(!compare){
                response.put("message","developpeurs not found pswd");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

            }else{
                String token = Jwts.builder()
                        .claim("data", userFrombd)
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);
                System.out.println("hhh");
                return ResponseEntity.status(HttpStatus.OK).body(response);

            }
        }
    }














}
