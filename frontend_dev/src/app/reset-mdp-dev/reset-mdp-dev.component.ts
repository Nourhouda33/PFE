import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Developpeurs } from '../Entity/Developpeurs.Entity';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { CrudService } from '../service/crud.service';

@Component({
  selector: 'app-reset-mdp-dev',
  templateUrl: './reset-mdp-dev.component.html',
  styleUrls: ['./reset-mdp-dev.component.css']
})
export class ResetMdpDevComponent {

  resetForm: FormGroup
  constructor(
    private fb: FormBuilder,
    private service:CrudService,
    private router:Router,
    private toast:NgToastService
  ){ 
    let formControls = {
      email: new FormControl('',[
        Validators.required,
      Validators.email]),}
      this.resetForm = this.fb.group(formControls)
}
get email() {return this.resetForm.get('email');}

resetmdpclt() {
  let data = this.resetForm.value;
  console.log(data);
  let developpeurs = new Developpeurs(
    data.null, data.null,data.null,data.email,data.null,data.null);
  console.log(developpeurs);

  if (
    data.email == 0
  ) {
    this.toast.info({
      detail: 'Error Message',
      summary: 'Champs obligatoire',
    });
  } else  (confirm("Verifier votre email a travers le lien envoyé dans votre boite email" +  " ?")); {
  this.service.resetMdpDeveloppeurs(developpeurs).subscribe(
    res=>{
      console.log(res);
      this.toast.error({
        detail: 'Error Message',
        summary: 'Probléme de Serveur',
      });

    },
    err=>{
      console.log(err);
      this.toast.success({
        detail: 'Succes Message',
        summary: 'Connexion avec succés',

      }); 
      this.router.navigate(['/login']);

    }
  )

  }
}

  ngOnInit(): void {
  }

}
