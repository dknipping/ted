package com.prodyna.cockpit.reservation.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prodyna.cockpit.reservation.dto.ReservationDTO;
import com.prodyna.cockpit.reservation.dto.ReservationsDTO;
import com.prodyna.cockpit.reservation.entity.Reservation;
import com.prodyna.cockpit.reservation.service.ReservationService;

/**
 * Created by mfroehlich on 24.04.2015.
 */
@RestController
@RequestMapping(value = "/reservations")
public class ReservationsResource {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ReservationsDTO> getReservations() {
        final Collection<Reservation> reservations = reservationService.findAllReservations();

        Collection<ReservationDTO> dtos = new ArrayList<>();
        for (Reservation res : reservations) {
            ReservationDTO dto = getReservationDTOFromReservation(res);
            dto.addLink(res.isRoom() ? createLinkToRoom(res.getItemId(), "self") : createLinkToDesk(res.getItemId(), "self"));
            dtos.add(dto);
        }

        ReservationsDTO result = new ReservationsDTO();
        result.setReservationDTOs(dtos);
        result.addLink(createLinkToGetReservations().withSelfRel());

        final ResponseEntity<ReservationsDTO> responseEntity = new ResponseEntity<ReservationsDTO>(
                result, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/room/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReservationDTO> getRoomReservationById(@RequestParam Long id) {
        final Reservation reservation = reservationService.findReservation(id);

        ReservationDTO dto = getReservationDTOFromReservation(reservation);
        dto.addLink(createLinkToCreateRoomReservation(null).withSelfRel());
        dto.addLink(createLinkToRoom(reservation.getItemId(), "room"));

        final ResponseEntity<ReservationDTO> responseEntity = new ResponseEntity<ReservationDTO>(dto, HttpStatus.OK);

        return responseEntity;
    }

    @RequestMapping(value = "/desk/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReservationDTO> getDeskReservationById(@RequestParam Long id) {
        final Reservation reservation = reservationService.findReservation(id);

        ReservationDTO dto = getReservationDTOFromReservation(reservation);
        dto.addLink(createLinkToCreateDeskReservation(null).withSelfRel());
        dto.addLink(createLinkToDesk(reservation.getItemId(), "desk"));

        final ResponseEntity<ReservationDTO> responseEntity = new ResponseEntity<ReservationDTO>(dto, HttpStatus.OK);

        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/room")
    public ResponseEntity<ReservationDTO> createRoomReservation(@RequestBody ReservationDTO reservation) {

        Reservation reservationToBeStored = new Reservation();
        reservationToBeStored.setFromDate(reservation.getFromDate());
        reservationToBeStored.setToDate(reservation.getToDate());
        reservationToBeStored.setDescription(reservation.getDescription());
        reservationToBeStored.setItemId(reservation.getItemId());
        reservationToBeStored.setRoom(true);
        reservationToBeStored.setUserId(reservation.getUserId());

        Reservation storedReservation = reservationService.createReservation(reservationToBeStored);

        ReservationDTO dto = getReservationDTOFromReservation(storedReservation);
        dto.addLink(createLinkToCreateRoomReservation(reservation).withSelfRel());
        dto.addLink(createLinkToRoom(reservation.getItemId(), "room"));

        return new ResponseEntity<ReservationDTO>(dto, HttpStatus.OK);
    }

    private ReservationDTO getReservationDTOFromReservation(Reservation storedReservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setUserId(storedReservation.getUserId());
        dto.setDescription(storedReservation.getDescription());
        dto.setEditDate(storedReservation.getEditDate());
        dto.setCreationDate(storedReservation.getCreationDate());
        dto.setFromDate(storedReservation.getFromDate());
        dto.setToDate(storedReservation.getToDate());
        dto.setId(storedReservation.getId());
        dto.setItemId(storedReservation.getItemId());

        return dto;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/desk")
    public ResponseEntity<ReservationDTO> createDeskReservation(@RequestBody ReservationDTO reservation) {

        Reservation reservationToBeStored = new Reservation();
        reservationToBeStored.setFromDate(reservation.getFromDate());
        reservationToBeStored.setToDate(reservation.getToDate());
        reservationToBeStored.setDescription(reservation.getDescription());
        reservationToBeStored.setItemId(reservation.getItemId());
        reservationToBeStored.setRoom(false);
        reservationToBeStored.setUserId(reservation.getUserId());

        Reservation storedReservation = reservationService.createReservation(reservationToBeStored);

        ReservationDTO dto = getReservationDTOFromReservation(storedReservation);
        dto.addLink(createLinkToCreateDeskReservation(reservation).withSelfRel());
        dto.addLink(createLinkToDesk(reservation.getItemId(), "desk"));

        return new ResponseEntity<ReservationDTO>(dto, HttpStatus.OK);
    }

    private Link createLinkToDesk(String deskId, String rel) {
        List<ServiceInstance> zuulInstances = discoveryClient.getInstances("zuul");
        ServiceInstance serviceInstance = zuulInstances.get(0);
        URI zuulUri = serviceInstance.getUri();
        String zuulService = zuulUri.toString();

        String itemHref = zuulService + "/desk/" + deskId;
        Link link = new Link(itemHref, rel);
        return link;
    }

    private Link createLinkToRoom(String roomId, String rel) {
        List<ServiceInstance> zuulInstances = discoveryClient.getInstances("zuul");
        ServiceInstance serviceInstance = zuulInstances.get(0);
        URI zuulUri = serviceInstance.getUri();
        String zuulService = zuulUri.toString();

        String itemHref = zuulService + "/room/" + roomId;
        Link link = new Link(itemHref, rel);
        return link;
    }

    private ControllerLinkBuilder createLinkToCreateDeskReservation(ReservationDTO reservationDTO) {
        return ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ReservationsResource.class)
                .createDeskReservation(reservationDTO));
    }

    private ControllerLinkBuilder createLinkToCreateRoomReservation(ReservationDTO reservationDTO) {
        return ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ReservationsResource.class)
                .createRoomReservation(reservationDTO));
    }

    private ControllerLinkBuilder createLinkToGetReservations() {
        return ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ReservationsResource.class)
                .getReservations());
    }
}
