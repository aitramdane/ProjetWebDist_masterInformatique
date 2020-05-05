package com.example.Hotel.roomstatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RoomStatusService")
public class RoomStatusServiceImpl implements IStatusService {
    @Autowired
    private IRoomStatusDao roomStatusDao;

    @Override
    public List<RoomStatus> getAllRoomStatus(){
        return roomStatusDao.findAll();
    }
}


