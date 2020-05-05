package com.example.Hotel.roomstatus;

import com.example.Hotel.room.Room;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROOM_STATUS")

public class RoomStatus {

    private Integer room_status_id;

    private String room_status;

    Set<Room> rooms = new HashSet<Room>();



    @Id
    @Column (name="STATUS_ID")

    public int getRoom_status_id() {
        return room_status_id;
    }

    public void setRoom_status_id(int room_status_id) {
        this.room_status_id = room_status_id;
    }

    @Column (name="STATUS")
    public String getRoom_status() {
        return room_status;
    }

    public void setRoom_status(String room_status) {
        this.room_status = room_status;
    }




    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room_status", cascade = CascadeType.ALL)
    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
