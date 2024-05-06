import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Tache } from '../Entity/Tache.Entity';
import { CrudService } from '../Service/crud.service';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { NgToastService } from 'ng-angular-popup';
import { ActivatedRoute, Router } from '@angular/router';
import { Developpeurs } from '../Entity/Developpeurs.Entity';
import { SaveTacheDeveloppeur } from '../Entity/SaveTacheDeveloppeur.Entity';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {
  todoForm: FormGroup;
  tasks: Tache[] = [];
  inprogress: Tache[] = [];
  done1: Tache[] = [];
  updateIndex: number | undefined;
  isEditEnabled: boolean = false;
  ListDeveloppeurs:Developpeurs[];
  champsEditable: boolean = false;
  boutonVisible: boolean = false;
  id:any;
  ListTache:Tache[]

  constructor(private fb: FormBuilder, private service: CrudService,private toast:NgToastService,  private route: Router,private router:ActivatedRoute) { 
     let formControls = {
      discription: new FormControl('',[
        Validators.required,]),
      status: new FormControl('',[
          Validators.required,
        ]),
        
        developpeurs: new FormControl('',[
          Validators.required,]),
  };
  this.todoForm = this.fb.group(formControls);}
  get discription() {return this.todoForm.get('discription');}
  get status() {return this.todoForm.get('status');}
  get developpeurs() {return this.todoForm.get('developpeurs');}


  ngOnInit(): void {
   
    this.service.getTasks().subscribe((tasks: Tache[]) => this.tasks = tasks);

    this.service.getDeveloppeurs().subscribe(tache => {
      this.ListDeveloppeurs = tache
    })

    let idEvent = this.router.snapshot.params['id'];
    this.id = idEvent;
    this.service.findTacheById(idEvent).subscribe((result) => {
      let event = result;
      console.log(event);
      this.todoForm.patchValue({
        discription: event.discription,
        status: event.status,
        developpeurs: event.developpeurs.prenom,
       
        
       });}); 
  }

  loadTasks() {
    this.service.getTasks().subscribe((tasks: Tache[]) => this.tasks = tasks);
  }
  addNewTache() {


   
    let data = this.todoForm.value;

    console.log(data);
    let model:SaveTacheDeveloppeur=new SaveTacheDeveloppeur();
    model.id=null;
    model.discription=data.discription;
    model.status=data.status;
    model.idDeveloppeurs=+data.developpeurs;
    this.loadTasks();
    this.todoForm.reset();

    if (
      data.discription == 0 ||
      data.developpeurs ==0 
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

        this.route.navigate(['/tâches']).then(()=>[window.location.reload()]);
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
 

  deleteTache(id: number) {
    this.service.deleteTask(id).subscribe(() => {
      this.loadTasks();
    });
  }

  deleteinprogress(id: number) {
    this.service.deleteTask(id).subscribe(() => {
      this.loadTasks();
    });
  }

  deletedone(id: number) {
    this.service.deleteTask(id).subscribe(() => {
      this.loadTasks();
    });
  }
  onEdit(item: Tache, index: number) {
    this.todoForm.controls['item'].setValue(item.discription);
    this.updateIndex = index;
    this.isEditEnabled = true;
  }

  updateTask() {
    if (this.updateIndex !== undefined) {
      const discription = this.todoForm.value.discription
      const id = this.tasks[this.updateIndex].id;
    const status = this.todoForm.value.status;
      this.service.updateTask(id, { discription, status }).subscribe(() => {
        this.loadTasks();
        this.todoForm.reset();
        this.updateIndex = undefined;
        this.isEditEnabled = false;
      });
    }
  }

  
  
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
