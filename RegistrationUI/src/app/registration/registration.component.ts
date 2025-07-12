import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NavigationExtras, Router } from '@angular/router';
import { User } from '../models/user';
import { DataService } from '../service/dataservice';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { LocalService } from '../Encrypt/local.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})

export class RegistrationComponent implements OnInit {
  showOtp:boolean = false;
  verificationPending:boolean = true;
  registrationForm : FormGroup;
  generatedOtp:String;
  enteredOtp:String;
  model: NgbDateStruct;
  newDate : Date;
  genders:any;
  users: any;

  constructor(private localService:LocalService,private dataService:DataService,private router:Router,private readonly fb: FormBuilder) {
    this.registrationForm = this.fb.group({
      first_name: [''],
      middle_name: [''],
      last_name: [''],
      mobileNo:[''],
      otpField:[''],
      gender:[''],
      dateofbirth:[''],
      email_id:['']
    });
   }

  ngOnInit(): void {
    this.dataService.getLoadRegistrationPage().subscribe(
      data=>{
        this.genders = data.Gender_List;
       console.log(this.users);
      },
      error =>{
        console.log(error)
      });

  }

  onSubmit(){  
    let users = new User();
    //converting ngbdDate from json to date string
    users = this.f.dateofbirth.value;
    var datePipe = new DatePipe('en-US');
    const newDate = datePipe.transform(new Date(users.year,users.month-1,users.day), 'dd-MM-yyyy')!;
    users.dateofbirth = newDate;
    users.first_name = this.f.first_name.value;
    users.middle_name = this.f.middle_name.value;
    users.last_name = this.f.last_name.value;
    users.mobileNo = this.f.mobileNo.value;
    users.gender = this.f.gender.value;
    users.email_id = this.f.email_id.value;
      this.dataService.registerUser(users).subscribe(
        data => {
          const navigationExtras: NavigationExtras = {state: {recieved_data: data}};
          this.router.navigate(['success'],navigationExtras); 
        },
        error => {
          console.log(error)
        }
      );
  }

  sendOtp(){
    let user = new User();
    user.mobileNo = this.f.mobileNo.value;
    this.dataService.sendOtp(user).subscribe(
      (data: any) => {
        this.localService.setJsonValue('GenOtp',data.otp);
        this.showOtp = true;
      },
      (error: any) => {
        console.log(error)
      }
    );
  }

  verifyOtp(){
   this.enteredOtp = this.f.otpField.value;
   this.generatedOtp = this.localService.getJsonValue('GenOtp');
    if(this.enteredOtp == this.generatedOtp){
      this.verificationPending = !this.verificationPending;
      this.showOtp = !this.showOtp;
    }
  }

  backtoLogin(){
    this.router.navigate(['login'])
  }

get f(){
    return this.registrationForm.controls;
  }
}
