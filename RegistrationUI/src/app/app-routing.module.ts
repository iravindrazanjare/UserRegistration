import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DocumentComponent } from './document/document.component';
import { InstructionsComponent } from './instructions/instructions.component';
import { LoginComponent } from './login/login.component';
import { PaymentComponent } from './payment/payment.component';
import { PersonalComponent } from './personal/personal.component';
import { PhotoComponent } from './photo/photo.component';
import { PreviewComponent } from './preview/preview.component';
import { RegistrationComponent } from './registration/registration.component';
import { SignComponent } from './sign/sign.component';
import { SuccessComponent } from './success/success.component';

const routes: Routes = [
  {path: 'registration', component: RegistrationComponent},
  {path: 'success', component:SuccessComponent},
  {path: 'login', component: LoginComponent},
  {path: 'instructions', component: InstructionsComponent},
  {path: 'personal', component: PersonalComponent},
  {path: 'photo', component:PhotoComponent},
  {path: 'sign', component:SignComponent},
  {path: 'documentUpload', component: DocumentComponent},
  {path: 'preview', component: PreviewComponent},
  {path: 'payment', component:PaymentComponent},
  {path: '', redirectTo: '/login', pathMatch: 'full'}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
