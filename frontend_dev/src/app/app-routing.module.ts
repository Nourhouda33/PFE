import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ResetMdpDevComponent } from './reset-mdp-dev/reset-mdp-dev.component';
import { PageServiceComponent } from './page-service/page-service.component';
import { ListTacheComponent } from './list-tache/list-tache.component';
import { ProfilComponent } from './profil/profil.component';
//import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'about',component:AboutComponent},
  {path:'Contact',component:ContactComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'RestMdp',component:ResetMdpDevComponent},
  {path:'Service',component:PageServiceComponent},
  {path:'Tache',component:ListTacheComponent},
  {path:'Profil',component:ProfilComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
