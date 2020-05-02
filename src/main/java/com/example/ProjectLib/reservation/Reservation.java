package com.example.ProjectLib.reservation;

import javax.persistence.*;

import  com.example.ProjectLib.client.Client;
import  com.example.ProjectLib.room.Room;

import java.time.LocalDate;


@Entity
@Table(name = "RESERVATION")

public class Reservation {

    private Integer reservationId;

    private Client client;

    private LocalDate reservationDate;

    private LocalDate check_date_in ;

    private LocalDate check_date_out;

    private int Number_adults;

    private int Number_children;

    private Room room;




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RESERVATION_ID")

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }


    @ManyToOne
    @JoinColumn(name = "RS_CLIENT_ID", referencedColumnName = "CLIENT_ID")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    @Column(name="RESERVATION_DATE")
    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Column(name = "CHECK_DATE_IN")
    public LocalDate getCheck_date_in() {
        return check_date_in;
    }

    public void setCheck_date_in(LocalDate check_date_in) {
        this.check_date_in = check_date_in;
    }

    @Column(name = "CHECK_DATE_OUT")
    public LocalDate getCheck_date_out() {
        return check_date_out;
    }

    public void setCheck_date_out(LocalDate check_date_out) {
        this.check_date_out = check_date_out;
    }

    @Column(name="NUMBER_ADULTS")
    public int getNumber_adults() {
        return Number_adults;
    }

    public void setNumber_adults(int number_adults) {
        Number_adults = number_adults;
    }


    @Column(name="NUMBER_CHILDREN")
    public int getNumber_children() {
        return Number_children;
    }

    public void setNumber_children(int number_children) {
        Number_children = number_children;
    }


    @ManyToOne
    @JoinColumn(name = "RS_ROOM_ID", referencedColumnName = "ID")
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
