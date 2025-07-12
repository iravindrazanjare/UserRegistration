import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { PersonalComponent } from './personal/personal.component';
import { HeaderComponent } from './header/header.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { RegistrationComponent } from './registration/registration.component';
import { SuccessComponent } from './success/success.component';
import { PhotoComponent } from './photo/photo.component';
import { SignComponent } from './sign/sign.component';
import { InstructionsComponent } from './instructions/instructions.component';
import { DocumentComponent } from './document/document.component';
import { PaymentComponent } from './payment/payment.component';
import { PreviewComponent } from './preview/preview.component';

export let browserRefresh = false;

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PersonalComponent,
    HeaderComponent,
    NavigationBarComponent,
    RegistrationComponent,
    SuccessComponent,
    PhotoComponent,
    SignComponent,
    InstructionsComponent,
    DocumentComponent,
    PaymentComponent,
    PreviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
