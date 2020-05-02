package com.example.ProjectLib.room;

import javax.persistence.*;

import com.example.ProjectLib.reservation.Reservation;
import com.example.ProjectLib.roomstatus.RoomStatus;
import com.example.ProjectLib.roomtype.RoomType;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "ROOM")
public class Room {

    private Integer roomId;

    private int floor_no;

    private RoomType room_type;

    private RoomStatus  room_status;

    Set<Reservation> reservations = new HashSet<Reservation>();




    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }



    @Column(name = "FLOOR_NO")
    public int getFloor_no() {
        return floor_no;
    }

    public void setFloor_no(int floor_no) {
        this.floor_no = floor_no;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    @ManyToOne
    @JoinColumn(name = "ROOM_TYPE_ID", referencedColumnName = "TYPE_ID")
    public RoomType getRoom_type() {
        return room_type;
    }

    public void setRoom_type(RoomType room_type) {
        this.room_type = room_type;
    }

    @ManyToOne
    @JoinColumn(name = "ROOM_STATUS_ID", referencedColumnName = "STATUS_ID")
    public RoomStatus getRoom_status() {
        return room_status;
    }

    public void setRoom_status(RoomStatus room_status) {
        this.room_status = room_status;
    }
}