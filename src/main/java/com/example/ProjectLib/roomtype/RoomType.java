package com.example.ProjectLib.roomtype;


import com.example.ProjectLib.room.Room;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ROOM_TYPE")
public class RoomType {

    private int room_type_id;

    private String type;

    Set<Room> rooms = new HashSet<Room>();


    @Id
    @Column(name = "TYPE_ID")
    public int getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(int room_type_id) {
        this.room_type_id = room_type_id;
    }


    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room_type", cascade = CascadeType.ALL)
    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
