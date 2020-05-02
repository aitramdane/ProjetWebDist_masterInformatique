import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Type } from "src/app/models/type";
import { Status } from "src/app/models/status";
import { Room } from "src/app/models/room";
import { NgForm } from "@angular/forms";
import { RoomService } from "src/app/services/room.service";
import { NgxSpinnerService } from 'ngx-spinner';



@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})


export class RoomComponent implements OnInit {

  public types = [ 'Type','Status'];
  public roomType : string; 
  public status : string; 
  public displayType: string= 'Type'
  public headsTab = ['No','Floor No','Type','Status'];
  public isNoResult: boolean = true;
  public isFormSubmitted: boolean = false;
  public actionButton: string = 'Save';
  public titleSaveOrUpdate: string = 'Add Rooms Form';
  public messageModal: string;
  public displayMessageModal: boolean = false;

  public roomtypes: Type[] = [] //à revoir
  public roomstatus: Status[] = [] // à revoir
  public room = new Room();
  public searchRoomsResult: Room[] = [];
  

  


  constructor(private roomService: RoomService,private spinner: NgxSpinnerService) { }

  ngOnInit(): void {
    this.roomService.loadTypes();
    this.roomService.loadStatus();
  }


  loadTypes(){
    this.spinner.show();
      this.roomService.loadTypes().subscribe(
              (result: Type[]) => {
                this.spinner.hide(); 
                 this.roomtypes.push.apply(this.roomtypes, result);
              },
              error => {
                this.spinner.hide();
                this.buildMessageModal('An error occurs when retreiving types');
              }
      );
  }

  loadStatus(){
    this.spinner.show();
      this.roomService.loadStatus().subscribe(
              (result: Status[]) => {
                this.spinner.hide(); 
                 this.roomstatus.push.apply(this.roomstatus, result);
              },
              error => {
                this.spinner.hide();
                this.buildMessageModal('An error occurs when retreiving types');
              }
      );
  }


  saveOrUpdateRoom(addRoomForm : NgForm){
    this.displayMessageModal = false;
      if(!addRoomForm.valid){
        this.buildMessageModal('Error in the form');
      }
      if(this.actionButton && this.actionButton === 'Save'){
          this.saveNewRoom(this.room);
      }else if(this.actionButton && this.actionButton === 'Update'){
          this.updateRoom(this.room);
      }
      this.titleSaveOrUpdate = 'Add New Room Form';
      this.actionButton = 'Save';

   }

   saveNewRoom(room: Room){
    this.spinner.show();
    this.roomService.saveRoom(room).subscribe(
            (result: Room) => {
               if(result.roomId){
                   this.spinner.hide();
                   this.buildMessageModal('Save operation correctly done');
               }
            },
            error => {
                this.spinner.hide();
                this.buildMessageModal('An error occurs when saving data');
            }
    );
}


updateRoom(room: Room){
  this.spinner.show();
  this.roomService.updateRoom(room).subscribe(
          (result: Room) => {
             if(result.roomId){
                 this.updateResearchRoomsTab(room);
                 this.spinner.hide();
                 this.buildMessageModal('Update operation correctly done');
             }
          },
          error => {
           this.spinner.hide();
           this.buildMessageModal('An error occurs when updating room data');
          }
  );
}




 updateResearchRoomsTab(room: Room){
    if(this.searchRoomsResult && this.searchRoomsResult.length > 0){
        let index : number = 0;
        this.searchRoomsResult.forEach(element => {
            if(element.roomId == room.roomId){
                this.searchRoomsResult.splice(index, 1, room);
            }
            index++;
        });
    }
}


setUpdateRoom(room: Room){
  this.titleSaveOrUpdate = 'Search Rooms Form'
  this.actionButton = 'Update';
  this.room = Object.assign({}, room);
  this.displayMessageModal = false;
}


deleteRoom(room: Room){
  this.spinner.show();
  this.displayMessageModal = false;
  this.roomService.deleteRoom(room).subscribe(
          result => {
              for( var i = 0; i < this.searchRoomsResult.length; i++){ 
                  if ( this.searchRoomsResult[i].roomId === room.roomId) {
                      this.searchRoomsResult.splice(i, 1); 
                  }
              }
              this.spinner.hide();
              this.buildMessageModal('End of delete operation');
              if(this.searchRoomsResult.length == 0){
                  this.isNoResult = true;
              }
          });
}


searchRoomsByType(searchRoomForm){
  this.spinner.show();
  this.displayMessageModal = false;
  if(!searchRoomForm.valid){
    this.buildMessageModal('Error in the form');
  }
  if(this.displayType === 'Type'){
      this.searchRoomsResult = [];
      this.roomService.searchRoomByType(this.roomType).subscribe(
              (result : Room[]) => {
                  if(result && result != null){
                      this.searchRoomsResult = result;
                      this.isNoResult = false;
                      this.spinner.hide();
                      return;
                  }
                  this.isNoResult = true;
                  this.spinner.hide();
              },
              error => {
                this.spinner.hide();
                this.buildMessageModal('An error occurs when searching the room data');
              }
      );
  }else if(this.displayType === 'Status'){
  this.searchRoomsResult = [];
  this.roomService.searchRoomByStatus(this.status).subscribe(
          (result : Room[]) => {
              if(result && result != null){
                  this.searchRoomsResult = result;
                  this.isNoResult = false;
                  this.spinner.hide();
                  return;
              }
              this.isNoResult = true;
              this.spinner.hide();
          },
          error => {
            this.spinner.hide();
            this.buildMessageModal('An error occurs when searching the room data');
          }
  );
}
this.isFormSubmitted = searchRoomForm.submitted;
}


clearForm(addRoomForm: NgForm){
  addRoomForm.form.reset(); 
  this.displayMessageModal = false;
}


buildMessageModal(msg: string){
  this.messageModal = msg;
  this.displayMessageModal = true;
}

getUrl() {
  return "url('./assets/background2.jpg')";
}


}
