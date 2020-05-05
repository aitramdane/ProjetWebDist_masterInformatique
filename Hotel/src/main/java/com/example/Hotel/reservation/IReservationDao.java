package com.example.Hotel.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IReservationDao extends JpaRepository<Reservation,Integer> {

    public List<Reservation> findReservationByreservationDate(LocalDate reservationDate);

    public Reservation findByReservationId(Integer roomId);

}

