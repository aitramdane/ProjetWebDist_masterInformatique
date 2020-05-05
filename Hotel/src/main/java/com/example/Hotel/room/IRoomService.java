package com.example.Hotel.room;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IRoomService {

    public Room saveRoom(Room room);

    public Room updateRoom(Room room);

    public Room findRoomByRoomId(@PathVariable(value = "roomId") Integer roomId);

    public boolean checkIfNoExists(@PathVariable(value = "roomId") Integer roomId);

    public List<Room>  getAllRoomsByType(String roomType);

    public List<Room>  getAllRoomsByStatus(String roomStatus);

    public void deleteRoom(Integer roomId);


}


