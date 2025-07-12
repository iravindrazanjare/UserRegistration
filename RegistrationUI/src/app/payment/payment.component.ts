import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { DataService } from '../service/dataservice';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  paymentData: FormGroup;
  encryptedUrl:string;
  constructor(private readonly fb: FormBuilder,private router:Router,private dataService:DataService,private http: HttpClient) { 
    this.paymentData = this.fb.group({
    });
  }

  ngOnInit(): void {
  }

  onSubmit(){
    this.dataService.generatePaymentRequest().subscribe(
      data => {
      this.encryptedUrl = data.message;
      this.UploadForm();
    });
  }
  UploadForm(){
      let body = new HttpParams();
      body = body.set('EncryptTrans', this.encryptedUrl);
      body = body.set('merchIdVal', 1000003);
      return this.dataService.makePayment(body);
    }
}
