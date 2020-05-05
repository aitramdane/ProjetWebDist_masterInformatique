package com.example.Hotel.reservation;


import java.time.LocalDate;
import java.util.List;

public interface IReservationService {

    public boolean checkIfReservationExists(Integer reservation_id);

    public Reservation saveReservation(Reservation reservation);

    public Reservation updateReservation(Reservation reservation);

    public void deleteReservation(Integer reservation_id);

    public Reservation findReservationByreservationId(Integer reservationId);

    public List<Reservation> findReservationByreservationDate(LocalDate reservationDate);




}
