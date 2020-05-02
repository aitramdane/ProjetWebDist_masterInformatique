package com.example.ProjectLib.reservation;

import com.example.ProjectLib.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IReservationDao extends JpaRepository<Reservation,Integer> {

    public List<Reservation> findReservationByreservationDate(LocalDate reservationDate);

    @Query("SELECT u from Reservation u " +
            "INNER JOIN u.client c " +
            "where c.email = ?1 ")
    public List<Reservation> getAllReservationOfThisClient(String email);

    public Reservation findByReservationId(Integer roomId);

}

