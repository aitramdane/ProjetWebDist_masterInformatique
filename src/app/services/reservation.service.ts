import { Injectable } from '@angular/core';
import { Reservation } from '../models/reservation';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Mail } from '../models/mail';




@Injectable({
  providedIn: 'root'
})


export class ReservationService {

  constructor(private http: HttpClient) { }


  
  updateReservation (reservation: Reservation): Observable<Reservation>{
    return this.http.put<Reservation>('http://localhost:8080/HotelSys/rest/reservation/api/updateReservation', reservation);
}

  saveReservation (reservation: Reservation): Observable<Reservation>{
  return this.http.post<Reservation>('http://localhost:8080/HotelSys/rest/reservation/api/addReservation', reservation);
}

  deleteReservation(reservation: Reservation): Observable<string> {
  return this.http.delete<string>('http://localhost:8080/HotelSys/rest/reservation/api/deleteReservation/'+reservation.reservationId);
}

  searchReservationById(id: Number): Observable<Reservation>{
  return  this.http.get<Reservation>('http://localhost:8080/HotelSys/rest/reservation/api/searchReservationById?id='+id);
}

  searchReservationByReservationDate(reservationDate: Date): Observable<Reservation[]>{
  let month : string = reservationDate.getMonth() < 10 ? '0'+(reservationDate.getMonth()+1): ''+(reservationDate.getMonth()+1);
  let dayOfMonth : string = reservationDate.getDate() < 10 ? '0'+reservationDate.getDate(): ''+reservationDate.getDate();
  let reservationDateStr : string = reservationDate.getFullYear() + '-' + month + '-' + dayOfMonth;
 return  this.http.get<Reservation[]>('http://localhost:8080/HotelSys/rest/reservation/api/searchByReservationDate?reservationDate='+reservationDateStr);
}


}