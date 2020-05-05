package com.example.Hotel.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoomDao extends JpaRepository<Room, Integer> {

    public Room findByRoomId(Integer roomId);

    @Query ( "SELECT u from Room u " +
            "INNER JOIN u.room_type r " +
            "where r.type = ?1 ")
    public List<Room>  getAllRoomsByType(String roomType);


    @Query ( "SELECT u from Room u " +
            "INNER JOIN u.room_status r " +
            "where r.room_status = ?1 ")
    public List<Room>  getAllRoomsByStatus(String roomStatus);
}


