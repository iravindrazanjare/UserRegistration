import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription';
import { timer } from 'rxjs';
import { map } from 'rxjs/internal/operators/map';
import { share } from 'rxjs/internal/operators/share';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {
  time = new Date();
  rxTime = new Date();
  intervalId :any;
  subscription: Subscription;
  constructor(private router:Router) { }

  ngOnInit(): void {
    // Using RxJS Timer
    this.subscription = timer(0, 1000)
      .pipe(
        map(() => new Date()),
        share()
      )
      .subscribe(time => {
        this.rxTime = time;
      });      
  }

    ngOnDestroy() {
        clearInterval(this.intervalId);
        if (this.subscription) {
          this.subscription.unsubscribe();
        }
    }  
  logout(){
    this.router.navigate(['login']);
  }

  openModal(){
    document.getElementById('modal1')!.style.display = 'block';
    document.body.classList.add('jw-modal-open');
}

closeModal(){
    document.getElementById('modal1')!.style.display = 'none';
    document.body.classList.remove('jw-modal-open');
}

}
