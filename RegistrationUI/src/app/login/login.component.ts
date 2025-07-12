import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators  } from '@angular/forms';
import { Router,NavigationExtras } from '@angular/router';
import { DataService } from '../service/dataservice';
import { CommonService } from '../shared/common.service';
import { LocalService } from '../Encrypt/local.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private localService:LocalService,private commonData: CommonService,private dataService:DataService,private readonly fb: FormBuilder, private router:Router) {
    this.loginForm = this.fb.group({
      loginId: ['', Validators.required],
      password: ['', Validators.required]
    });
  }
    

  ngOnInit(): void {
  }

  onSubmit() {
    this.dataService.authenticateUser(this.loginForm.getRawValue()).subscribe(
      data => {
        this.commonData.setLoggedInUser(data);
        this.localService.setJsonValue('loggedInUser',data);
        this.router.navigate(['instructions']);
        //const navigationExtras: NavigationExtras = {state: {recieved_data: data}};
        //this.router.navigate(['personal'], navigationExtras);
      },
      error => {
        console.log(error)
      }
    );
  }
  LoadRegistration(){
    this.router.navigate(['registration']);
  }
}
