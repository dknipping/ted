package com.prodyna.cockpit.reservation.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.prodyna.cockpit.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prodyna.cockpit.reservation.entity.Reservation;

/**
 * Created by mfroehlich on 24.04.2015.
 */
@RestController
@RequestMapping(value = "/reservations")
public class ReservationsResource {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Reservation>> getReservations() {
        final List<Reservation> reservations = Collections.<Reservation> emptyList();
        final ResponseEntity<Collection<Reservation>> responseEntity = new ResponseEntity<Collection<Reservation>>(
                reservations, HttpStatus.OK);

        return responseEntity;
    }

    @RequestMapping(value="/{reservationId}", method = RequestMethod.GET)
    public ResponseEntity<Reservation> getReservation(@RequestParam Long reservationId) {
        final Reservation reservation = reservationService.findReservation(reservationId);
        final ResponseEntity<Reservation> responseEntity = new ResponseEntity<Reservation>(
                reservation, HttpStatus.OK);

        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation storedReservation = reservationService.createReservation(reservation);
        return new ResponseEntity<Reservation>(storedReservation, HttpStatus.OK);
    }
}
