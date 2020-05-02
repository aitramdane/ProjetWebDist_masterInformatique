package com.example.ProjectLib.roomstatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomStatusDao extends JpaRepository <RoomStatus, Integer> {
}
