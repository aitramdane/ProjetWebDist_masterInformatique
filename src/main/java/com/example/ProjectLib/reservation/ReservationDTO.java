package com.example.ProjectLib.reservation;


import com.example.ProjectLib.client.Client;
import com.example.ProjectLib.client.ClientDTO;
import com.example.ProjectLib.room.Room;
import com.example.ProjectLib.room.RoomDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@ApiModel(value = "Reservation Model")
public class ReservationDTO implements Comparable <ReservationDTO> {


    @ApiModelProperty (value = "Reservation id")
    private Integer reservationId;

   @ApiModelProperty(value = "Room concerned for the reservation")
    private Integer roomId;

    @ApiModelProperty(value = "Client concerned by the reservation")
    private Integer clientId;

    @ApiModelProperty (value = "Reservation creation Date")
    private LocalDate reservationDate;

    @ApiModelProperty(value = "Reservation beginning date")
    private LocalDate check_date_in ;

    @ApiModelProperty(value = "Reservation ending date")
    private LocalDate check_date_out;

    @ApiModelProperty(value = "Number of persons")
    private Integer Number_adults;

    @ApiModelProperty(value = "Number of children ")
    private Integer Number_children;


    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }


    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }


    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalDate getCheck_date_in() {
        return check_date_in;
    }

    public void setCheck_date_in(LocalDate check_date_in) {
        this.check_date_in = check_date_in;
    }


    public LocalDate getCheck_date_out() {
        return check_date_out;
    }

    public void setCheck_date_out(LocalDate check_date_out) {
        this.check_date_out = check_date_out;
    }


    public int getNumber_adults() {
        return Number_adults;
    }

    public void setNumber_adults(int number_adults) {
        Number_adults = number_adults;
    }


    public int getNumber_children() {
        return Number_children;
    }

    public void setNumber_children(int number_children) {
        Number_children = number_children;
    }

    @Override
    public int compareTo (ReservationDTO o ) {
        return  this.reservationId.compareTo(o.reservationId);
    }
}
