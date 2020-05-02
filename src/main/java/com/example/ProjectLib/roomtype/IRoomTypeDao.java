package com.example.ProjectLib.roomtype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomTypeDao extends JpaRepository <RoomType, Integer> {

}
