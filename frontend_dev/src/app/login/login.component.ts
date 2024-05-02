import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators ,FormsModule, ReactiveFormsModule} from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { CrudService } from '../service/crud.service';
import { Developpeurs } from '../Entity/Developpeurs.Entity';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  
})
export class LoginComponent {
  loginForm: FormGroup
  constructor(
    private fb: FormBuilder,
    private service:CrudService,
    private router:Router,private toast:NgToastService
  ) { 
    let formControls = {
      email: new FormControl('',[
        Validators.required,
        Validators.email
        
      ]),
      pswd: new FormControl('',[
        Validators.required,
       
      ])
    }

    this.loginForm = this.fb.group(formControls)
  }

  get email() { return this.loginForm.get('email') }
  get pswd() { return this.loginForm.get('pswd') }
  ngOnInit(): void { 
  }

  login() {
    let data = this.loginForm.value;
    console.log(data);
    let developpeurs = new Developpeurs(null,null,null,data.email,data.pswd,null,null);
    console.log(developpeurs);
    if (
  
      data.email == 0 ||
      data.pswd == 0
    )
    {
      this.toast.info({
        detail: 'Error Message',
        summary: 'Remplir votre champs',
      });
    } else {
  
      this.service.loginDeveloppeurs(developpeurs).subscribe(
        res=>{
          console.log(res);
          let token = res.token;
          localStorage.setItem("myToken",res.token);
          localStorage.setItem("role",res.role);
          this.router.navigate(['/Profil']).then(()=>window.location.reload());
      },
       
        err=>{
          console.log(err);
          this.toast.error({
            detail: 'Error Message',
            summary: 'Probléme de Serveur',
          });
          
        }
      )
      
    }
    }
}
