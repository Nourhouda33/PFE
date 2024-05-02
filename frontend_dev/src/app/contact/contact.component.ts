import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { CrudService } from '../service/crud.service';
import { Contact } from '../Entity/Contact.Entity';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {

  userDetails:any;
    ContactForm: FormGroup
  constructor(private service :CrudService,private router:Router,private fb:FormBuilder,private toast:NgToastService) 
    { this.userDetails=this.service.userDetails();
    let formControls = {
      
      nom: new FormControl('',[
        Validators.required,
       
      ]),
      email: new FormControl('',[
        Validators.required,
        Validators.email
        
      ]),
      sujet: new FormControl('',[
        Validators.required,
       
      ]),
      
      phone: new FormControl('',[
        Validators.required,
       
      ]),
      
      message: new FormControl('',[
        Validators.required,
       
      ])

    }

    this.ContactForm = this.fb.group(formControls)
  }

  get email() { return this.ContactForm.get('email') }
  get nom() { return this.ContactForm.get('nom') }
  get sujet() { return this.ContactForm.get('sujet') }
  get phone() { return this.ContactForm.get('phone') }
  get message() { return this.ContactForm.get('message') }

  ngOnInit(): void { 
  }

  AddContact() {
    let data = this.ContactForm.value;
    console.log(data);
    let contact = new Contact( undefined,data.nom,data.email,data.phone,data.sujet,data.message);
    console.log(contact);
    if (
      data.nom== 0 ||
      data.email == 0 ||
      data.phone== 0 ||
      data.sujet== 0
      
    )
    {
      this.toast.info({
        detail: 'Error Message',
        summary: 'Remplir votre champs',
      });
    }   else {
          this.service.addContact(contact).subscribe(
            res=>{
              console.log(res);
              this.toast.success({
                detail: 'Succes Message',
                summary: 'Contact est Envoyé avec succés',
              });
      
               this.router.navigate(['/']);
            },
            err=>{
              console.log(err);
              this.toast.error({
                detail: 'Error Message',
                summary: 'Probléme de Serveur',
              });}
          
      
              )

            }
          }
        }        