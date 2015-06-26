package com.prodyna.cockpit.reservation.service;

import com.prodyna.cockpit.reservation.entity.Reservation;
import com.prodyna.cockpit.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by mfroehlich on 24.04.2015.
 */
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation findReservation(Long reservationId) {
        Reservation res = reservationRepository.findOne(reservationId);
        return res;
    }

    public Reservation createReservation(Reservation reservation) {
        Reservation savedReservation = reservationRepository.save(reservation);
        return savedReservation;
    }

    public Collection<Reservation> findAllReservations() {
        Collection<Reservation> resCol = reservationRepository.findAll();
        return resCol;
    }
}
