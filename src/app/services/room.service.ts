import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { Type } from "src/app/models/type";
import { Status } from "src/app/models/status";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Room } from "src/app/models/room";


@Injectable({
  providedIn: 'root'
})

export class RoomService {

  constructor(private http: HttpClient) { }

  loadTypes(): Observable<Type[]>{
    let headers = new HttpHeaders();
    headers.append('content-type', 'application/json');
    headers.append('accept', 'application/json');
    return this.http.get<Type[]>('http://localhost:8080/HotelSys/rest/type/api/allTypes', {headers: headers});
}

  loadStatus(): Observable<Status[]>{
  let headers = new HttpHeaders();
  headers.append('content-type', 'application/json');
  headers.append('accept', 'application/json');
  return this.http.get<Status[]>('http://localhost:8080/HotelSys/rest/status/api/allStatus', {headers: headers});

}

saveRoom(room: Room): Observable<Room>{
  return this.http.post<Room>('http://localhost:8080/HotelSys/rest/room/api/addRoom', room);
}


deleteRoom(room: Room): Observable<string>{
  return this.http.delete<string>('http://localhost:8080/HotelSys/rest/room/api/deleteRoom/'+room.roomId);
}



  updateRoom(room: Room): Observable<Room>{
    return this.http.put<Room>('http://localhost:8080/HotelSys/rest/room/api/updateRoom', room);
}


  searchRoomByRoomNo(roomId: Number): Observable<Room>{
  return  this.http.get<Room>('http://localhost:8080/HotelSys/rest/room/api/searchByNo?roomId='+roomId);
}

  searchRoomByStatus(status: string): Observable<Room[]>{
  return this.http.get<Room[]>('http://localhost:8080/HotelSys/rest/room/api/RoomByStatus?room_status='+status);
}

  searchRoomByType(type: string): Observable<Room[]>{
  return this.http.get<Room[]>('http://localhost:8080/HotelSys/rest/room/api/RoomByType?room_type='+type);
}














































}
