import { Component } from '@angular/core';
import { Admin } from '../Entity/Admin.Entity';
import { CrudService } from '../service/crud.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {
  listadmin:Admin[]
constructor(private service:CrudService ){
  
this.service.getAdmin().subscribe(admin=>
  this.listadmin=admin
)
}
}
