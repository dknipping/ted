package com.prodyna.cockpit.reservation.dto;

import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by mfroehlich on 25.06.2015.
 */
public class ReservationDTO {

    private Long id;
    private String description;

    private String userId;
    private Date fromDate;
    private Date toDate;
    private Date creationDate;

    private Date editDate;
    private String itemId;

    private List<Link> links;

    public ReservationDTO() {
        this.links = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public void addLink(Link link) {
        this.links.add(link);
    }

    public List<Link> getLinks() {
        return Collections.unmodifiableList(links);
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
