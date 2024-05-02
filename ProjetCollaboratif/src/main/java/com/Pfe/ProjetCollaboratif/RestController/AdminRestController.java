package com.Pfe.ProjetCollaboratif.RestController;

import com.Pfe.ProjetCollaboratif.Entity.Admin;
import com.Pfe.ProjetCollaboratif.Repository.AdminRepository;
import com.Pfe.ProjetCollaboratif.Service.AdminService;
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

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/Admin")
public class AdminRestController {
    //cree une instance de BcryptPasswordEncoder pour encoder les mots de passe en tout sercurite
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired

    AdminRepository adminRepository;

    @Autowired
    AdminService adminService;
    //mappe les requetes HTTP POST  a la 'ajouterAdmin' methode
    @RequestMapping(method = RequestMethod.POST )
    //la methode responsable du traitement de la reqest POST POUR AJOUTER UN ADMINISTA
    //IL PRENDRE UN "Admin" objet comme corps de la requete '@RequrstBody' et renvoie un 'requestEntity

    ResponseEntity<?> AjouterAdmin (@RequestBody Admin admin){
        //un variable pur stocker la reponse
        HashMap<String, Object> response = new HashMap<>();
        if(adminRepository.existsByEmail(admin.getEmail())){
            response.put("message", "email exist deja !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            admin.setPswd(this.bCryptPasswordEncoder.encode(admin.getPswd()));
            Admin savedUser = adminRepository.save(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);}

    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Admin> AfficherAdmin(){
        return adminService.getAdmin();
    }
   /* @RequestMapping(method =RequestMethod.DELETE)
    public void supprimerContact(Long id){
        contactService.SupprimerContact(id);
    }*/

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
    public void Deleteadmin(@PathVariable("id") Long id){
        adminService.Deleteadmin(id);

    }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Admin> getAdminById(@PathVariable("id") Long id){

        Optional<Admin> admin = adminService.getAdminById(id);
        return admin;
    }
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public ResponseEntity<Admin> ModifierAdmin(@PathVariable("id")Long id, @RequestBody Admin adminDetails){
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            admin.setNom(adminDetails.getNom());
            admin.setPrenom(adminDetails.getPrenom());
            admin.setEmail(adminDetails.getEmail());
            admin.setPswd(adminDetails.getPswd());
            admin.setAdresse(adminDetails.getAdresse());
            admin.setPhone(adminDetails.getPhone());

            Admin updatedAdmin = adminRepository.save(admin);
            return ResponseEntity.ok(updatedAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }}


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginAdmin(@RequestBody Admin admin) {
        System.out.println("in login-admin"+admin);
        HashMap<String, Object> response = new HashMap<>();

        Admin userFromDB = adminRepository.findAdminByEmail(admin.getEmail());
        System.out.println("userFromDB+admin"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "Admin not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(admin.getPswd(), userFromDB.getPswd());
            System.out.println("compare"+compare);
            if (!compare) {
                response.put("message", " Erreur Mot de passe  !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else
            {
                String token = Jwts.builder()
                        .claim("data", userFromDB)
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);
                System.out.println("True");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

        }
    }


















}
