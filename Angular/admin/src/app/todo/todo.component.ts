import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { SousTache } from '../Entity/SousTache.Entity';
import { SaveTacheDeveloppeur } from '../Entity/SaveTacheDeveloppeur.Entity';
import { Tache } from '../Entity/Tache.Entity';
import { CrudService } from '../Service/crud.service';
import { Projet } from '../Entity/Projet.Entity';
import { Equipe } from '../Entity/Equipe.Entity';
import { Developpeurs } from '../Entity/Developpeurs.Entity';
import { SaveSousTache } from '../Entity/SaveSousTache.Entity';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent {
                  

  SousTacheForm:FormGroup;
    todoForm: FormGroup;
    tasks: Tache[] = [];
    inprogress: Tache[] = [];
    done: Tache[] = [];
    updateIndex: number | undefined;
    isEditEnabled: boolean = false;
    ListDeveloppeur:Developpeurs[];
    ListProjet:Projet[];
    ListEquipe:Equipe[];
    champsEditable: boolean = false;
    boutonVisible: boolean = false;
    id:any;
    ListTache:Tache[]
    tache:Tache;
    idprojet:number;
    idtache:number;
    EquipeProjet:Projet[];
    ListSousTache:SousTache[];
    userFile :any
  message:any
  imgURL:any
  fichierURL:any
  imagePath:any
    constructor(private fb: FormBuilder, private service: CrudService,private toast:NgToastService,  private route: Router,private router:ActivatedRoute) { 
       let formControls = {
        nom: new FormControl('',[
          Validators.required,]),
        date: new FormControl('',[
            Validators.required,
          ]),
        discription: new FormControl('',[
          Validators.required,]),
        status: new FormControl('',[
            Validators.required,
          ]),
          
            projet: new FormControl('',[
              Validators.required,]),
    };
    this.todoForm = this.fb.group(formControls);}
    get nom() {return this.todoForm.get('nom');}
    get date() {return this.todoForm.get('date');}

    get discription() {return this.todoForm.get('discription');}
    get status() {return this.todoForm.get('status');}
    get projet() {return this.todoForm.get('projet');}

  
  
    ngOnInit(): void {
      let idEvent = this.router.snapshot.params['id'];
      this.id = idEvent;
      this.service.getTasks().subscribe(tache => {
        this.ListTache = tache
      })
      this.service.getEquipeByProjet(this.id.equipe.id).subscribe(dev =>{
        this.EquipeProjet = dev
      })
  
      this.idtache = this.tache.id;
      this.id = this.idtache;
      this.service.findTacheById(this.idtache).subscribe((result) => {
       this.tache = result;
        console.log(this.tache);
        this.todoForm.patchValue({
          nom: this.tache.nom,
          date:this.tache.date,
          projet: this.tache.projet.equipe.developpeurs
         
          
         });}); 
  
       
          this.service.findProjetById(idEvent).subscribe((result) => {
            let event = result;
            console.log(event);
            this.todoForm.patchValue({
           
              
             
             });}); }
              addNewTache() {
  
  
                let idEvent = this.router.snapshot.params['id'];
                
                let data = this.todoForm.value;
            
                console.log(data);
                let model:SaveTacheDeveloppeur=new SaveTacheDeveloppeur();
                model.id=null;

                model.nom=data.nom;
                model.date=data.date;
                model.idProjet=idEvent;


                this.loadTasks();
                this.todoForm.reset();
            
                if (
                  data.nom == 0 ||
                  data.date == 0 
                ) {
                  this.toast.info({
                    detail: 'Error Message',
                    summary: 'Votre champs est obligatoire',
                  });
                } else {
                this.service.addTask(model).subscribe(
                  res=>{
                    console.log(res);
                    this.toast.success({
                      detail: 'Succes Message',
                      summary: 'Tache est Envoyé avec succés',
                    });
            
                    this.service.getTasks().subscribe((tasks: Tache[]) => this.tasks = tasks);

                                        //parceque je peux pas acceder a la page Tache lorsque j'ajout un Tache
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
    loadTasks() {
      this.service.getTasks().subscribe((tasks: Tache[]) => this.tasks = tasks);
    }
    onSelectIMG(event: any) {
      if (event.target.files.length > 0) {
        const file = event.target.files[0];
        this.userFile = file;
        var mimeType = event.target.files[0].type;
        if (mimeType.match(/image\/*/) == null) {
          this.message = 'Only images are supported.';
          return;
        }
        var reader = new FileReader();
        this.imagePath = file;
        reader.readAsDataURL(file);
        reader.onload = (_event) => {
          this.imgURL = reader.result;
        };
      }
    }
    onSelectFile(event: any) {
      if (event.target.files.length > 0) {
        const file = event.target.files[0];
        this.userFile = file;
        var mimeType = event.target.files[0].type;
        if (mimeType.match(/fichier\/*/) == null) {
          this.message = 'Only images are supported.';
          return;
        }
        var reader = new FileReader();
        this.imagePath = file;
        reader.readAsDataURL(file);
        reader.onload = (_event) => {
          this.fichierURL = reader.result;
        };
      }
    }
    addNewSousTache() {
      let data = this.SousTacheForm.value;
      console.log(data);
      let idEvent = this.router.snapshot.params['id'];
                
     
  
      
      let model:SaveSousTache=new SaveSousTache();
      model.id=null;

      model.description=data.description;
      model.commentaire=data.commentaire;  
      model.fichier=this.fichierURL;
      model.image=this.imgURL;
      model.checkList=data.checkList;
      model.idTache=data.id;


      this.loadTasks();
      this.todoForm.reset();
      if (
        data.description == 0 ||
        data.commentaire == 0
    
      ) {
        this.toast.info({
          detail: 'Error Message',
          summary: 'Votre champs est obligatoire',
        });
        return; // Arrête l'exécution de la fonction si des champs obligatoires sont manquants
      } 
    
      if (!this.imgURL) {
        this.toast.info({
          detail: 'Error Message',
          summary: 'Veuillez sélectionner une image',
        });
        return; // Arrête l'exécution de la fonction si aucune image n'est sélectionnée
      }
    
      let sousTache = new SousTache(
        undefined, 
        data.description,
        data.commentaire,
        this.fichierURL,
        this.imgURL, // Utilise l'URL de l'image sélectionnée
        data.checkList,
     
      );
      console.log(sousTache);
    
      this.service.addDeveloppeurs(sousTache).subscribe(
        res => {
          console.log(res);
          this.toast.success({
            detail: 'Succes Message',
            summary: 'sousTache est Envoyé avec succès',
          });
          this.service.getSousTache().subscribe(sTache => {
            this.ListSousTache = sTache
          })      },
        err => {
          console.log(err);
          this.toast.error({
            detail: 'Error Message',
            summary: 'Problème de Serveur',
          });
        }
      );
    }
   
  
    deleteTask(id: number) {
      this.service.deleteTask(id).subscribe(() => {
        this.loadTasks();
      });
    }
  
    deleteinprogress(id: number) {
      this.service.deleteTask(id).subscribe(() => {
        this.service.getTasks().subscribe((tasks: Tache[]) => this.tasks = tasks);
      });

    }
  
    deletedone(id: number) {
      this.service.deleteTask(id).subscribe(() => {
        this.service.getTasks().subscribe((tasks: Tache[]) => this.tasks = tasks);
      });
    }
   /* onEdit(item: Tache, index: number) {
      this.todoForm.controls['item'].setValue(item.nom);
      this.updateIndex = index;
    }*/
  
    UpdateTache(id: number) {
      let data = this.todoForm.value;
      let tache =new Tache(
        
        data.nom,
        data.date, 
        
     
    );
      console.log(tache);
      console.log(data);
      this.service.updateTask(this.id,tache).subscribe((res) => {
        console.log(res);
        
        this.service.getTasks().subscribe(projet => {
          this.ListTache= projet
  })}); }
  
    
    
    drop(event: CdkDragDrop<Tache[]>) {
      if (event.previousContainer === event.container) {
        moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
      } else {
        transferArrayItem(
          event.previousContainer.data,
          event.container.data,
          event.previousIndex,
          event.currentIndex,
        );
      }
    }
  
  
  
  
  
  
  
  
  
    
    basculerEditionChamps() {
      this.champsEditable = !this.champsEditable;
      if (this.champsEditable) {
        this.todoForm.enable();
        this.boutonVisible = true;
      } else {
        this.todoForm.disable();
        this.boutonVisible = false;
      }
    }
  
 
  
  
  







}
