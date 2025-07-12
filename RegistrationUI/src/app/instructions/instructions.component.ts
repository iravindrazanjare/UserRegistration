import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.css']
})
export class InstructionsComponent implements OnInit {
  displayStyle: string;
  forgotPassword: FormGroup;

  constructor(private router:Router,private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.openPopup();
    this.forgotPassword = this.formBuilder.group({
      current_pass: ['',[Validators.required]],
      new_pass:['',[Validators.required]],
      confirm_pass:['',[Validators.required]]

    })
  }

  continueToPersonal(){
    this.router.navigate(['personal']);
  }

  
  closePopup(){
    this.displayStyle = "none";
    
  }
  openPopup(){
    this.displayStyle = "block";
  }

  updatePassword(){

  }

  get j() {
    return this.forgotPassword.controls;
  }
}
