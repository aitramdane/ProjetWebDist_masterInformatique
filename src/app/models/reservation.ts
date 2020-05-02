import { Room } from "src/app/models/room"; 
import {Client} from "src/app/models/client"

export class Reservation {

    reservationId : Number;

    reservationDate : Date; 

    check_date_in : Date; 

    check_date_out : Date; 

    number_adults : Number;

    number_children: Number; 

    clientId : Number; 

    roomId :  Number; 


}