package com.example.Hotel.reservation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service("ReservationService")
@Transactional
public class ReservationServiceImpl implements  IReservationService {

    @Autowired
    private IReservationDao reservationDao;


    @Override
    public boolean checkIfReservationExists(Integer reservationId) {
        return reservationDao.existsById(reservationId);
    }
    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationDao.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return reservationDao.save(reservation);
    }

    @Override
    public void deleteReservation(Integer reservation_id ) {
        reservationDao.deleteById(reservation_id);
    }

    @Override
    public Reservation findReservationByreservationId (Integer reservationId) {
        return reservationDao.findByReservationId(reservationId);
    }

    @Override
    public List<Reservation> findReservationByreservationDate(LocalDate reservationDate) {
        return reservationDao.findReservationByreservationDate(reservationDate);
    }




}