import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatInputModule} from '@angular/material/input';
import { MatNativeDateModule} from '@angular/material/core';
import { MAT_DATE_LOCALE} from '@angular/material/core'
import { NgxSpinnerModule } from 'ngx-spinner';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientComponent } from './client/client.component';
import { RoomComponent } from './room/room.component';
import { ReservationComponent } from './reservation/reservation.component';
import { BackToMenuComponent } from './menu/back-to-menu/back-to-menu.component';
import { MenuComponent } from './menu/menu/menu.component';
import { MailModalComponent } from './modal/mail-modal/mail-modal.component';
import { MessageModalComponent } from './modal/message-modal/message-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientComponent,
    RoomComponent,
    ReservationComponent,
    BackToMenuComponent,
    MenuComponent,
    MailModalComponent,
    MessageModalComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,   /* for using form elements like NgForm */
    HttpClientModule, /* for using http request elements end verbs like GET, POST, ... */
    MatDatepickerModule, MatInputModule,MatNativeDateModule, BrowserAnimationsModule, ReactiveFormsModule, /* for using input date picker -> need to install @angular/material package*/
    NgxSpinnerModule /* for using spinner */
  ],

  providers: [{provide: MAT_DATE_LOCALE, useValue: 'en-GB'}],
  bootstrap: [AppComponent],
  schemas : [CUSTOM_ELEMENTS_SCHEMA]

})
export class AppModule { }
