package com.example.ProjectLib.roomtype;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("RoomTypeService")
public class RoomTypeServiceImpl implements ITypeService {

    @Autowired
    private IRoomTypeDao roomTypeDao;

    @Override
    public List<RoomType> getAllRoomTypes(){
        return roomTypeDao.findAll();
    }
}
