import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { CrudService } from '../service/crud.service';
import { Developpeurs } from '../Entity/Developpeurs.Entity';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

 
  developpeurForm:FormGroup
  constructor(private service :CrudService,private router:Router,private fb:FormBuilder,private toast:NgToastService) {
    let formControls = {
      nom: new FormControl('',[
        Validators.required,]),
      prenom: new FormControl('',[
        Validators.required,]),
      email: new FormControl('',[
          Validators.required,
        Validators.email]),
      pswd: new FormControl('',[
        Validators.required,
        Validators.maxLength(8)]),
      
   }
     this.developpeurForm = this.fb.group(formControls)
   }
   get nom() {return this.developpeurForm.get('nom');}
  get prenom() { return this.developpeurForm.get('prenom');}
  get email() {return this.developpeurForm.get('email');}
  get pswd() {return this.developpeurForm.get('pswd');}
 

   addNewdeveloppeur() {
    let data = this.developpeurForm.value;
    console.log(data);
    let developpeur = new Developpeurs(
     undefined, data.nom,data.prenom,data.email,data.pswd);
    console.log(developpeur);

    if (
      data.nom == 0 ||
      data.prenom == 0||
      data.email == 0 ||
      data.pswd == 0 
     
      
    ) {
      this.toast.info({
        detail: 'Error Message',
        summary: 'Votre champs est obligatoire',
      });
    } else 
      (confirm("Verifier votre email a travers le lien envoyé dans votre boite email" +  " ?")); {
    this.service.addDeveloppeurs(developpeur).subscribe(
      res=>{
        console.log(res);
        this.router.navigate(['/login']).then(()=>window.location.reload());
        this.toast.success({
          detail: 'Succes Message',
          summary: 'developpeuristrateur est Envoyé avec succés',
        });

       

      },
      err=>{
        console.log(err);
        this.toast.error({
          detail: 'Error Message',
          summary: 'Probléme de Serveur',
        }); }
    )

    }
  }

  //supprimer

    ngOnInit(): void {
    }
}
