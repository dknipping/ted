package com.prodyna.cockpit.reservation.repository;

import com.prodyna.cockpit.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mfroehlich on 24.04.2015.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
