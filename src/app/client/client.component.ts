import { Component, OnInit } from '@angular/core';
import { Type } from "src/app/models/type";
import { Status } from "src/app/models/status";
import { Room } from "src/app/models/room";
import { NgForm } from "@angular/forms";
import { RoomService } from "src/app/services/room.service";
import { Client } from 'src/app/models/client';
import { ClientService } from 'src/app/services/client.service';
import { NgxSpinnerService } from 'ngx-spinner';



@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})

export class ClientComponent implements OnInit {

  public types = [ 'Email', 'Last Name'];
  public email: string;
  public lastName: string;
  public clientId : Number;
  public displayType: string = 'Email'
  public headsTab = ['FIRST NAME', 'LAST NAME', 'EMAIL', 'JOB', 'ADDRESS','Country','City','Phone'];
  public isNoResult: boolean = true;
  public isFormSubmitted: boolean = false;
  public actionButton: string = 'Save';
  public titleSaveOrUpdate: string = 'Add New Client';
  public messageModal: string;
  public displayMessageModal: boolean = false;
  public isDisplaySendEmailForm: boolean = false;
  public disabledBackground : boolean = false;


  public client = new Client();
  public searchClientsResult: Client[] = [];



  constructor(private clientService: ClientService, private spinner: NgxSpinnerService) { }

  ngOnInit(): void {
  }


  
 saveOrUpdateClient(addClientForm: NgForm){
  this.displayMessageModal = false;
  if(!addClientForm.valid){
    this.buildMessageModal('Error in the form');
  }
  if(this.actionButton && this.actionButton === 'Save'){
      this.saveNewClient(this.client);
  }else if(this.actionButton && this.actionButton === 'Update'){
      this.updateClient(this.client);
  }
  this.titleSaveOrUpdate = 'Add New Client Form';
  this.actionButton = 'Save';
}

 

 saveNewClient(client: Client){
  this.spinner.show();
  this.clientService.saveClient(client).subscribe(
          (result: Client) => {
             if(result.id){
                 this.spinner.hide();
                 this.buildMessageModal('Save operation correctly done');
             }
          },
          error => {
              this.spinner.hide();
              this.buildMessageModal('An error occurs when saving the client data0');
          }
  );
}


   
  updateClient(client: Client){
    this.spinner.show();
    this.clientService.updateClient(client).subscribe(
            (result: Client) => {
               if(result.id){
                   this.updateResearchClientTab(client);
                   this.spinner.hide();
                   this.buildMessageModal('Update operation correctly done');
               }
            },
            error => {
             this.spinner.hide();
             this.buildMessageModal('An error occurs when updating the client data1');
            }
    );
}



   updateResearchClientTab(client: Client){
    let index : number = 0; 
    if(this.searchClientsResult && this.searchClientsResult.length > 0){
        this.searchClientsResult.forEach(element => {
            if(element.id == client.id){
                this.searchClientsResult.splice(index, 1, client);
            }
            index++;
        });
    }
}




   deleteClient(client: Client){
    this.spinner.show();
    this.displayMessageModal = false;
    this.clientService.deleteClient(client).subscribe(
            result => {
                for( var i = 0; i < this.searchClientsResult.length; i++){ 
                    if ( this.searchClientsResult[i].id === client.id) {
                        this.searchClientsResult.splice(i, 1); 
                    }
                }
                this.spinner.hide();
                this.buildMessageModal('End of delete operation');
                if(this.searchClientsResult.length == 0){
                    this.isNoResult = true;
                }
            });
}

   
  setUpdateClient(client: Client){
    this.titleSaveOrUpdate = 'Update Client Form';
    this.actionButton = 'Update';
    this.client = Object.assign({}, client);
}

 


clearForm(addCustomerForm: NgForm){
    addCustomerForm.form.reset(); 
    this.displayMessageModal = false;
}


 
 searchClientsByType(searchClientForm){
  this.spinner.show();
  this.displayMessageModal = false;
  if(!searchClientForm.valid){
    this.buildMessageModal('Error in the form');
  }
  if(this.displayType === 'Email'){
      this.searchClientsResult = [];
      this.clientService.searchClientByEmail(this.email).subscribe(
              result => {
                  if(result && result != null){
                      this.searchClientsResult.push(result);
                      this.isNoResult = false;
                      this.spinner.hide();
                      return;
                   }
                   this.isNoResult = true;
                   this.spinner.hide();
              },
              error => {
                  this.spinner.hide();
                  this.buildMessageModal('An error occurs when searching the client data2');
              }
      );
  } else if(this.displayType === 'Last Name'){
      this.searchClientsResult = [];
      this.clientService.searchClientByLastName(this.lastName).subscribe(
              result => {
                  if(result && result != null){
                      this.searchClientsResult = result;
                      this.isNoResult = false;
                      this.spinner.hide();
                      return;
                  }
                  this.isNoResult = true;
                  this.spinner.hide();
              },
              error => {
                  this.spinner.hide();
                  this.buildMessageModal('An error occurs when searching the client data3');
              }
      );
  }
  this.isFormSubmitted = searchClientForm.submitted;
}

  
  buildMessageModal(msg: string){
    this.messageModal = msg;
    this.displayMessageModal = true;
  }


  
displaySendEmailForm(id: number){
    this.clientId = id;
    this.isDisplaySendEmailForm = true;
    this.disabledBackground = true;
    this.displayMessageModal = false;
   }
  
   closeEmailForm(){
    this.isDisplaySendEmailForm = false;
    this.disabledBackground = false;
    this.displayMessageModal = false;
   }

  getUrl() {
    return "url('./assets/background2.jpg')";
  }



}
