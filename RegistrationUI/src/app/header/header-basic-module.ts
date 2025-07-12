import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { HeaderComponent } from './header.component';

@NgModule({
  imports: [BrowserModule, NgbModule],
  declarations: [HeaderComponent],
  exports: [HeaderComponent],
  bootstrap: [HeaderComponent]
})
export class NgbdDropdownBasicModule {}
