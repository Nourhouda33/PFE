import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Projet } from '../Entity/Projet.Entity';
import { CrudService } from '../Service/crud.service';
import { Developpeurs } from '../Entity/Developpeurs.Entity';
import { Equipe } from '../Entity/Equipe.Entity';
import { Tache } from '../Entity/Tache.Entity';

@Component({
  selector: 'app-modifier-projet',
  templateUrl: './modifier-projet.component.html',
  styleUrls: ['./modifier-projet.component.css']
})
export class ModifierProjetComponent implements OnInit {
  ListEquipe: Equipe[];
  id: number;
  updateForm: FormGroup;
  ListProjet: Projet[];
  Listequipe: Equipe[];
  ListDeveloppeur: Developpeurs[];
  Listtache: Tache[];
  userFile: any;
  message: any;
  imagePath: any;
  imgURL: any;
  projet: Projet;
  champsEditable: boolean = false;
  boutonVisible: boolean = false;

  currentProjet = new Projet();

  constructor(
    private fb: FormBuilder,
    private service: CrudService,
    private route: Router,
    private router: ActivatedRoute
  ) {
    this.updateForm = this.fb.group({
      nom: ['', [Validators.required, Validators.pattern("[a-z A-Z .'-]+"), Validators.minLength(4)]],
      dateDebuit: ['', Validators.required],
      dateFin: ['', Validators.required],
      discription: ['', Validators.required],
      logo: ['', Validators.required],
      status: ['', Validators.required],
      equipe: ['', Validators.required]
    });

    
  }

  ngOnInit(): void {
  

    const idEvent = this.router.snapshot.params['id'];
    this.id = idEvent;
    this.service.findProjetById(idEvent).subscribe(result => {
      this.projet = result;
      this.updateForm.patchValue({
        logo: this.projet.logo,
        nom: this.projet.nom,
        dateDebuit: this.projet.dateDebuit,
        dateFin: this.projet.dateFin,
        discription: this.projet.discription,
        status: this.projet.status,
        equipe: this.projet.equipe.nom
      });
      this.ListDeveloppeur = this.projet.equipe.developpeurs;
    });

    this.service.getProjet().subscribe(projet => {
      this.ListProjet = projet;
    });

    this.service.getDeveloppeurs().subscribe(developpeurs => {
      this.ListDeveloppeur = developpeurs;
    });
    this.service.getEquipe().subscribe(equipe => {
      this.Listequipe = equipe;
    });
  }

  UpdateProjet() {
    const data = this.updateForm.value;
    const projet = new Projet(
      this.id,
      this.imgURL,
      data.nom,
      data.dateDebuit,
      data.dateFin,
      data.discription,
      data.status,
      this.projet.equipe
    );

    this.service.updateProjet(this.id, projet).subscribe(res => {
      this.service.getProjet().subscribe(projet => {
        this.ListProjet = projet;
      });
    });
  }

  onSelectFile(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.userFile = file;
      const mimeType = event.target.files[0].type;
      if (mimeType.match(/image\/*/) == null) {
        this.message = 'Only images are supported.';
        return;
      }
      const reader = new FileReader();
      this.imagePath = file;
      reader.readAsDataURL(file);
      reader.onload = (_event) => {
        this.imgURL = reader.result;
      };
    }
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
