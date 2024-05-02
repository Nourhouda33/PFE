import { Component } from '@angular/core';
import { Tache } from '../Entity/Tache.Entity';
import { Router } from '@angular/router';
import { CrudService } from '../service/crud.service';

@Component({
  selector: 'app-list-tache',
  templateUrl: './list-tache.component.html',
  styleUrls: ['./list-tache.component.css']
})
export class ListTacheComponent {
  ListTache: Tache[];
  p:number=1;
 collection:any[]
  
   constructor(private service:CrudService,private router:Router ) { }
 
 
   //supprimer
   DeleteTache(tache: Tache){
     if(confirm("Voulez vous supprimer cet tache avec l'ID " + tache.id + " ?")) {
      
       this.service.onDeleteTache(tache.id).subscribe(() => {
         this.router.navigate(['/listTache']).then(() => {
           window.location.reload()
         })
       })
    
   }
 }
 
 
   ngOnInit(): void {
     this.service.getTache().subscribe(tache => {
       this.ListTache = tache
     })
   }
 
}
