import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ReservationService } from '../../services/reservation.service';
import { RoomService } from '../../services/room.service';
import { ClientService } from '../../services/client.service';
import { HttpClient } from '@angular/common/http';
import { NgxSpinnerService } from 'ngx-spinner';
import { NgForm } from '@angular/forms';
import { Mail } from '../../models/mail';


@Component({
  selector: 'app-mail-modal',
  templateUrl: './mail-modal.component.html',
  styleUrls: ['./mail-modal.component.css']
})
export class MailModalComponent implements OnInit {
  
  @Input('clientId') clientId: number;

  @Output() unDisplayModal = new EventEmitter();

  public emailSubject : string = '';
  public emailContent: string = '';

  constructor( 
    private clientService: ClientService, private http: HttpClient, private spinner: NgxSpinnerService) { }


  ngOnInit() {
  }
  

    
  closeEmailForm(){
    this.unDisplayModal.emit("");
  }

   /**
    * Send email to client
    * @param sendEmailForm 
    */
   sendEmail(sendEmailForm: NgForm){
     this.spinner.show();
     let mail = new Mail();
     mail.clientId = this.clientId;
     if(sendEmailForm.value.subject && sendEmailForm.value.content){
       mail.emailSubject = sendEmailForm.value.subject;
       mail.emailContent = sendEmailForm.value.content;
       this.clientService.sendEmail(mail).subscribe(
         result => {
           if(result){
             this.spinner.hide();
             window.alert('Email sent');
             this.clientId = -1;
             this.emailContent = '';
             this.emailSubject = '';
          }
         },
         error => {
           this.spinner.hide();
           console.log(error);
           window.alert(error);
         }
       );
     }
   }

}
