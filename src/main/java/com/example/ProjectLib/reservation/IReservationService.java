package com.example.ProjectLib.reservation;


import com.example.ProjectLib.client.Client;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface IReservationService {

    public boolean checkIfReservationExists (Integer reservation_id);

    public Reservation saveReservation (Reservation reservation);

    public Reservation updateReservation (Reservation reservation);

    public void deleteReservation (Integer reservation_id);

    public Reservation findReservationByreservationId (Integer reservationId);

    public List<Reservation> findReservationByreservationDate( LocalDate reservationDate);

    public List<Reservation> getAllReservationOfThisClient (String email);



}
