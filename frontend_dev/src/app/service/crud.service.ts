import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Developpeurs } from '../Entity/Developpeurs.Entity';
import { Contact } from '../Entity/Contact.Entity';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Tache } from '../Entity/Tache.Entity';
import { Admin } from '../Entity/Admin.Entity';

@Injectable({
  providedIn: 'root'
})
export class CrudService {
  apiUrl="http://localhost:8081/api"
  loginUserUrl="http://localhost:8081/api/Developpeurs/login"
  resetMdpClt="http://localhost:8081/api/Developpeurs/forgotmdp"

  helper=new JwtHelperService()

  constructor(private http:HttpClient) { }
  resetMdpDeveloppeurs(developpeurs:Developpeurs){
    return this.http.post<any>(this.resetMdpClt, developpeurs);
    }

  
  getDeveloppeurs(): Observable<Developpeurs[]>{
    return this.http.get<Developpeurs[]>(this.apiUrl + "/Developpeurs");
  }
  addContact(contact:Contact){
    return this.http.post<any>(this.apiUrl+"/Contact", contact);
  }


  loginDeveloppeurs(developpeurs:Developpeurs){
    return this.http.post<any>(this.loginUserUrl, developpeurs);
  }
  addDeveloppeurs(developpeurs:Developpeurs)
   {
    return this.http.post<any>(this.apiUrl+"/Developpeurs",developpeurs);
   
  }
  onDeleteDeveloppeurs(id : number){
    const url =`${this.apiUrl+"/Developpeurs"}/${id}` 
    return this.http.delete(url)
  }
  onDeleteTache(id : number){
    const url =`${this.apiUrl+"/Tache"}/${id}` 
    return this.http.delete(url)
  }
  getTache(): Observable<Tache[]>{
    return this.http.get<Tache[]>(this.apiUrl + "/Tache");
  }
  getAdmin(): Observable<Admin[]>{
    return this.http.get<Admin[]>(this.apiUrl + "/Admin");
  }
  userDetails(){
    let token:any=localStorage.getItem('myToken');
    let decodeToken= this.helper.decodeToken(token);
     return decodeToken.data;
   }

   findDevById(id : number): Observable<Developpeurs> {
    const url = `${this.apiUrl + "/Developpeurs"}/${id};`
    return this.http.get<Developpeurs>(url)
  }
  updateDeveloppeurs(id:number,developpeurs: Developpeurs) {
    const url = `${this.apiUrl+"/Developpeurs"}/${id}`
    return this.http.put<any>(url, developpeurs);
  }

}
