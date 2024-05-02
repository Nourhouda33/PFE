import { Component } from '@angular/core';
import { CrudService } from '../service/crud.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  
  totalProjet:number=0;
  totalTache : number=0;
  totalContact: number=0;
 
  constructor(
    private service:CrudService,
  ) { 
    
  }


}
