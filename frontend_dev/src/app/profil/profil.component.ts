import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Developpeurs } from '../Entity/Developpeurs.Entity';
import { Router, ActivatedRoute } from '@angular/router';
import { CrudService } from '../service/crud.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent {
  idDev: number;
  userDetails:any;
  updateForm:FormGroup;

  champsEditable: boolean = false;
  boutonVisible: boolean = false;

  developpeur: Developpeurs = new Developpeurs();
  constructor(
    private fb: FormBuilder,
    private service: CrudService,
    private route: Router,
    private router: ActivatedRoute
  ) {
  
    this.userDetails = this.service.userDetails();
  
    this.updateForm = this.fb.group({
      nom: [this.userDetails ? this.userDetails.nom : '', [
        Validators.required,
      
      ]],
      prenom: [this.userDetails ? this.userDetails.prenom : '', [Validators.required]],
      email: [this.userDetails ? this.userDetails.email : '', [Validators.required]],
      pswd: [this.userDetails ? this.userDetails.pswd : '', [Validators.required]],
      adresse: [this.userDetails ? this.userDetails.adresse : '', [Validators.required]],
      tel: [this.userDetails ? this.userDetails.tel : '', [Validators.required]],
    });
  
    this.updateForm.disable();
  }
  
  
  
  ngOnInit(): void {
    this.idDev = this.userDetails.id;
  
    this.service.findDevById(this.idDev).subscribe(
      (result) => {
        // Succès : mettre à jour le formulaire avec les détails de l'admin
        this.updateForm.patchValue({
          nom: result.nom,
          prenom: result.prenom,
          email: result.email,
          pswd: result.pswd,

          adresse: result.adresse,
          tel: result.tel
        });
      },
    
    );
  }
  
  
  updateDeveloppeurs() {
    let data = this.updateForm.value;
    let developpeur =new Developpeurs(
        this.idDev,
        data.nom,
        data.prenom,
        data.email,
        data.pswd,
        data.adresse,
        data.tel
      );
      console.log(developpeur);
      console.log(data);
      this.service.updateDeveloppeurs(this.idDev, developpeur).subscribe((res) => {
        console.log(res);
        this.route.navigate(['/Profil']).then(()=>window.location.reload());
      });
    } 
  
    logout(){
      console.log("logout");
      localStorage.clear()
     
      this.route.navigate(['/login']);
     
    } 



    basculerEditionChamps() {
      this.champsEditable = !this.champsEditable;
      if (this.champsEditable) {
        this.updateForm.enable();
        this.boutonVisible = true;
      } else {
        this.updateForm.disable();
        this.boutonVisible = false;
      }
    }
}
