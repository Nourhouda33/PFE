package com.Pfe.ProjetCollaboratif.RestController;

import com.Pfe.ProjetCollaboratif.Entity.Client;
import com.Pfe.ProjetCollaboratif.Repository.ClientRepository;
import com.Pfe.ProjetCollaboratif.Service.ClientService;
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

@RequestMapping(value = "/client")
@CrossOrigin("*")
@RestController
public class ClientrestController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
     @Autowired
    ClientRepository clientRepository;

     @Autowired
    ClientService clientService;
     @RequestMapping(method = RequestMethod.POST)
     ResponseEntity<?> AjouteClient (@RequestBody Client client){
         HashMap<String , Object> response =new HashMap<>();
         if(clientRepository.existsByEmail(client.getEmail())){
             response.put("message","email exist deja !");
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
         }else{
             client.setPswd(this.bCryptPasswordEncoder.encode(client.getPswd()));
             Client savedUser =clientRepository.save(client);
             return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
         }
     }
     @RequestMapping(method = RequestMethod.GET)
    public List<Client> AfficherClient(){
         return clientService.getClient();
     }
     @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Optional<Client> AffichierAdminById(@PathVariable("id") Long id){
         Optional<Client> client=clientService.getClientById(id);
         return client;
     }

     @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void DeletClient(@PathVariable("id") Long id){
         clientService.supprimerClient(id);

     }

     @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Client ModifierClient(@PathVariable("id") Long id,@RequestBody Client client){
         client.setPswd((this.bCryptPasswordEncoder.encode(client.getPswd())));
         Client newclient = clientService.modifierClient(client);
         return newclient;
     }

     @PostMapping("/Login")
    public ResponseEntity<Map<String,Object>> LoginClient(@RequestBody Client client){
         System.out.println("in Login-client"+client);
         HashMap<String,Object> response=new HashMap<>();
         Client userfromDB= clientRepository.findByEmail(client.getEmail());
         System.out.println("user from bd +admin"+userfromDB);
         if(userfromDB ==null){
             response.put("message","client not found");
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
         }else{
             boolean compare= this.bCryptPasswordEncoder.matches(client.getPswd(), userfromDB.getPswd());
             System.out.println("compare"+compare);
             if(!compare){
                 response.put("message","client not found");
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

             }else
             {
                 String token = Jwts.builder()
                         .claim("data", userfromDB)
                         .signWith(SignatureAlgorithm.HS256, "SECRET")
                         .compact();
                 response.put("token", token);
                 System.out.println("hhh");
                 return ResponseEntity.status(HttpStatus.OK).body(response);
             }

         }

     }




























}
