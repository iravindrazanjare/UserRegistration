import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Personal } from '../models/personal';
import { CommonService } from '../shared/common.service';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {
  user: Personal;
  showPersonalNav:boolean;
  constructor(private commonService: CommonService) { }

  ngOnInit(): void {
    
  }

  showPersonal(){
    this.user = this.commonService.getLoggedInUser();
    if(this.user.candidateStage == 3){
      this.showPersonalNav = true;
      alert('its true');
    }else{
      this.showPersonalNav = false;
      alert('its false');
    }
  }

}
