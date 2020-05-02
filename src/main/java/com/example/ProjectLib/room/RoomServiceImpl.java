package com.example.ProjectLib.room;

import java.util.List;

import com.example.ProjectLib.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roomService")
@Transactional
public class RoomServiceImpl implements IRoomService {

    @Autowired
    private IRoomDao roomDao;

    @Override
    public Room saveRoom (Room room) {
        return (Room) roomDao.save(room);
    }

    @Override
    public Room updateRoom (Room room) {
        return (Room) roomDao.save(room);
    }

    @Override
    public boolean checkIfNoExists(Integer roomId) {
    return roomDao.existsById(roomId);
    }

    @Override
    public Room findRoomByRoomId (Integer roomId) {
        return roomDao.findByRoomId(roomId);
    }

    @Override
    public List<Room>  getAllRoomsByType (String roomType) {
        return roomDao.getAllRoomsByType(roomType);
    }

    @Override
    public List<Room>  getAllRoomsByStatus (String roomStatus) {
        return roomDao.getAllRoomsByStatus(roomStatus);
    }

    @Override
    public void deleteRoom(Integer roomId ) {
        roomDao.deleteById(roomId);
    }




}
