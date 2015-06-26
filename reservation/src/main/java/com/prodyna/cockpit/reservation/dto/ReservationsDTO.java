package com.prodyna.cockpit.reservation.dto;

import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mfroehlich on 25.06.2015.
 */
public class ReservationsDTO {
    private Collection<ReservationDTO> reservationDTOs;
    private List<Link> links;

    public ReservationsDTO() {
        this.links = new ArrayList<>();
    }

    public Collection<ReservationDTO> getReservationDTOs() {
        return reservationDTOs;
    }

    public void setReservationDTOs(Collection<ReservationDTO> reservationDTOs) {
        this.reservationDTOs = reservationDTOs;
    }

    public void addLink(Link link) {
        this.links.add(link);
    }
    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
