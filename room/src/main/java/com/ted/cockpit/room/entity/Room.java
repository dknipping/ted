package com.ted.cockpit.room.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room implements Serializable {

	private static final long serialVersionUID = 6291834871230951235L;

	@Id
	private String id = UUID.randomUUID().toString();
	
	private String buildingName;
	
	private int floorNumber;
	
	@ElementCollection
	private List<String> deskIds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

    public List<String> getDeskIds() {
        return deskIds;
    }

    public void setDeskIds(List<String> deskIds) {
        this.deskIds = deskIds;
    }
	
	

}
