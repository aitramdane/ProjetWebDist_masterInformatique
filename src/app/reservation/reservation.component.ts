import { Component, OnInit } from '@angular/core';
import { Reservation } from 'src/app/models/reservation';
import { NgForm } from '@angular/forms';
import { Mail } from 'src/app/models/mail';
import { ReservationService } from 'src/app/services/reservation.service';
import { RoomService } from 'src/app/services/room.service';
import { ClientService } from 'src/app/services/client.service';
import { Room } from 'src/app/models/room';
import { Client } from 'src/app/models/client';
import { forkJoin } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})


export class ReservationComponent implements OnInit {

  public types = [ 'Reservation Date'];
  public email: string;
  public reservationDate: Date;
  public reservationId : Number; 
  public displayType: string = 'Email'
  public headsTab = ['RESERVATION ID','ROOM ID', 'CLIENT ID', 'DATE CHECK IN', 'DATE CHECK OUT', 'ADULTS NUMBER','CHILDREN NUMBER'];
  public isNoResult: boolean = true;
  public isFormSubmitted: boolean = false;
  public actionButton: string = 'Save';
  public titleSaveOrUpdate: string = 'Add New Reservation Form';
  public clientId: number;
  public roomId: number;
  public disabledBackground : boolean = false;
  public messageModal: string;
  public displayMessageModal: boolean = false;
  public  checkDateIn : Date; 
  public checkDateOut : Date;


  public reservation = new Reservation (); 
  public searchReservationsResult: Reservation[] = [];





  constructor(private reservationService: ReservationService, private roomService: RoomService, 
  private clientService: ClientService, private http: HttpClient, private spinner: NgxSpinnerService) { 
 }

  ngOnInit(): void {
  }




  saveReservation(addReservationForm: NgForm){
    this.displayMessageModal = false;
  if(!addReservationForm.valid){
    this.buildMessageModal('Error in the form');
  }
  if(this.actionButton && this.actionButton === 'Save'){
      this.saveNewReservation(this.reservation);
  }else if(this.actionButton && this.actionButton === 'Update'){
      this.updateReservation(this.reservation);
  }
  this.titleSaveOrUpdate = 'Add New reseravarion Form';
  this.actionButton = 'Save';
}

/*
    this.spinner.show();
    this.displayMessageModal = false;
    if(!addReservationForm.valid){
        window.alert('Error in the form');
    }
    let  room = this.http.get<Room>('http://localhost:8080/HotelSys/rest/room/api/searchByNo?roomId='+this.reservation.roomId);
    let client = this.http.get<Client>('http://localhost:8080/HotelSys/rest/client/api/searchByEmail?email='+this.reservation..email);
    forkJoin([room, client]).subscribe(results => {
      if((results[0] && results[0].roomId) && (results[1] && results[1].id)){
        this.reservation.roomId = results[0].roomId;
        this.reservation.clientId = results[1].id;
        this.saveNewReservation(this.reservation);
      }else{
        this.buildMessageModal('An error occurs when saving data. May be data are not correct');
        this.spinner.hide();
      }
    });   
    }
*/





saveNewReservation(reservation: Reservation){
  reservation.check_date_in = this.setLocalDateDatePicker(reservation.check_date_in);
  reservation.check_date_out = this.setLocalDateDatePicker(reservation.check_date_out);
  
  this.reservationService.saveReservation(reservation).subscribe(
          (result: Reservation) => {
             if(result.reservationId){
                 this.spinner.hide();
                 this.buildMessageModal('Save operation correctly done');
             }
          },
          error => {
              this.spinner.hide();
              this.buildMessageModal('An error occurs when saving data1');
          }
  );
}


updateReservation(reservation: Reservation){
  this.spinner.show();
  this.reservationService.updateReservation(reservation).subscribe(
          (result: Reservation) => {
             if(result.reservationId){
                 this.updateResearchReservationTab(reservation);
                 this.spinner.hide();
                 this.buildMessageModal('Update operation correctly done');
             }
          },
          error => {
           this.spinner.hide();
           this.buildMessageModal('An error occurs when updating data');
          }
  );
}


updateResearchReservationTab(reservation: Reservation){
  let index : number = 0; 
  if(this.searchReservationsResult && this.searchReservationsResult.length > 0){
      this.searchReservationsResult.forEach(element => {
          if(element.reservationId == reservation.reservationId){
              this.searchReservationsResult.splice(index, 1,reservation);
          }
          index++;
      });
  }
}





setLocalDateDatePicker(date: Date): Date {
  var localDate = new Date(date);
  if(localDate.getTimezoneOffset() < 0){
      localDate.setMinutes(localDate.getMinutes() - localDate.getTimezoneOffset() );
  }else{
    localDate.setMinutes(localDate.getMinutes() + localDate.getTimezoneOffset() );
  }
  return localDate;
}


 
deleteReservation(reservation: Reservation){
  this.spinner.show();
  this.displayMessageModal = false;
  this.reservationService.deleteReservation(reservation).subscribe(
          result => {
              for( var i = 0; i < this.searchReservationsResult.length; i++){ 
                  if ( this.searchReservationsResult[i].reservationId === reservation.reservationId) {
                      this.searchReservationsResult.splice(i, 1); 
                  }
              }
              this.spinner.hide();
              this.buildMessageModal('End of delete operation');
              if(this.searchReservationsResult.length == 0){
                  this.isNoResult = true;
              }
          });
}


setUpdateReservation(reservation: Reservation){
  this.titleSaveOrUpdate = 'Update Reservation Form';
  this.actionButton = 'Update';
  this.reservation = Object.assign({}, reservation);
}

clearForm(addLoanForm: NgForm){
  addLoanForm.form.reset(); 
  this.displayMessageModal = false;
}



searchReservationsByReservationDate(searchReservationForm : NgForm){
  this.spinner.show();
  this.displayMessageModal = false;
  if(!searchReservationForm.valid){
      window.alert('Error in the form');
  }
 /* if(this.displayType === 'Email'){
      this.searchReservationsResult = [];
      this.reservationService.searchReservationById(this.reservationId).subscribe(
        result => {
          if(result && result != null){
              this.searchReservationsResult.push(result);
              this.isNoResult = false;
              this.spinner.hide();
              return;
           }
           this.isNoResult = true;
           this.spinner.hide();
      },

              error => {
                this.spinner.hide();
                this.buildMessageModal('An error occurs when searching the reservations data');
              }
      );
  } else*/ if(this.displayType === 'Reservation Date'){
      this.searchReservationsResult = [];
      this.reservationService.searchReservationByReservationDate(this.reservationDate).subscribe(
              (result: Reservation[]) => {
                this.treatResult(result);
                this.spinner.hide();
              },
              error => {
                this.spinner.hide();
                this.buildMessageModal('An error occurs when searching  data');
              }
      );
  }
  this.isFormSubmitted = searchReservationForm.submitted;
}



treatResult(result: Reservation[]){
  if(result && result != null){
      this.searchReservationsResult = result;
      this.isNoResult = false;
      return;
  }
  this.isNoResult = true;
}



 buildMessageModal(msg: string){
  this.messageModal = msg;
  this.displayMessageModal = true;
}


getUrl() {
  return "url('./assets/background2.jpg')";
}


}













