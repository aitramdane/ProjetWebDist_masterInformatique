import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client } from '../models/client';
import { Mail } from '../models/mail';




@Injectable({
  providedIn: 'root'
})



export class ClientService {

  constructor(private http: HttpClient) { }

    
    saveClient(client : Client): Observable<Client>{
      return this.http.post<Client>('http://localhost:8080/HotelSys/rest/client/api/addClient', client);
  }
  
   updateClient(client: Client): Observable<Client>{
       return this.http.put<Client>('http://localhost:8080/HotelSys/rest/client/api/updateClient', client);
   }
   
   
    deleteClient(client: Client): Observable<string>{
        return this.http.delete<string>('http://localhost:8080/HotelSys/rest/client/api/deleteClient/'+client.id);
    }
  

  searchClientByEmail(email: string): Observable<Client>{
      return  this.http.get<Client>('http://localhost:8080/HotelSys/rest/client/api/searchByEmail?email='+email);
  }
  
 
  searchClientByLastName(lastName: string): Observable<Client[]>{
          return this.http.get<Client[]>('http://localhost:8080/HotelSys/rest/client/api/searchByLastName?lastName='+lastName);
  }


  sendEmail(mail: Mail): Observable<boolean>{
    return this.http.put<boolean>('http://localhost:8080/HotelSys/rest/client/api/sendEmailToClient', mail);
  }


}


