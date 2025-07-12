import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NavigationExtras, Router } from '@angular/router';
import { LocalService } from '../Encrypt/local.service';
import { Personal } from '../models/personal';
import { DataService } from '../service/dataservice';
import { CommonService } from '../shared/common.service';
@Component({
  selector: 'app-personal',
  templateUrl: './personal.component.html',
  styleUrls: ['./personal.component.css']
})
export class PersonalComponent implements OnInit {
  user : Personal;
  personalDetails : FormGroup;
  selectedState:number;
  states: number;
  //recieved_data:string;
  constructor(private localService:LocalService,private commonData:CommonService,private router:Router,private readonly fb: FormBuilder,private dataService:DataService) { 
    this.personalDetails = this.fb.group({
      first_name: [''],
      middle_name: [''],
      last_name: [''],
      mobileNo:[''],
      otpField:[''],
      gender:[''],
      dateofbirth:[''],
      email_id:[''],
      addressline1:[''],
      addressline2:[''],
      city:[''],
      state:[''],
      pincode:['']
    });

    //recieving value from login navigation
    //const navigation = this.router.getCurrentNavigation()!;
    //const state = navigation.extras.state as {recieved_data: any};
    //this.user = state.recieved_data;
  }

  ngOnInit(): void {
    this.user = this.commonData.getLoggedInUser();
    if(this.user == null){
      this.user = this.localService.getJsonValue('loggedInUser');
      this.states = this.user.stateMap;
      this.selectedState = this.user.state;
    }
    //check if candidate Details filled
    this.dataService.getPersonalDetails(this.user).subscribe(
      data=>{
        if(data != null){
            this.user = data;
            this.states = this.user.stateMap;
            this.selectedState = this.user.state;
        }else{
              this.user = this.commonData.getLoggedInUser();
              this.user = this.localService.getJsonValue('loggedInUser');
              this.states = this.user.stateMap;
              this.selectedState = this.user.state;
        }
      },
      error =>{
        console.log(error)
      });
}

  onSubmit(){
    let personal = new Personal();
    personal.user_fk = this.user.user_fk;
    personal.first_name = this.f.first_name.value;
    personal.middle_name = this.f.middle_name.value;
    personal.last_name = this.f.last_name.value;
    personal.mobileNo = this.f.mobileNo.value;
    personal.gender = this.f.gender.value;
    personal.dateofbirth = this.f.dateofbirth.value;
    personal.email_id = this.f.email_id.value;
    personal.addressline1 = this.f.addressline1.value;
    personal.addressline2 = this.f.addressline2.value;
    personal.city = this.f.city.value;
    personal.state = this.f.state.value;
    personal.pincode = this.f.pincode.value;

    this.dataService.savePersonalDetails(personal).subscribe(
      data => {
        const navigationExtras: NavigationExtras = {state: {recieved_data: data}};
        this.router.navigate(['photo'],navigationExtras); 
      },
      error => {
        console.log(error)
      }
    );
}

  get f(){
    return this.personalDetails.controls;
  }

}
