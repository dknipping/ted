package com.ted.cockpit.room.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ted.cockpit.room.entity.Room;
import com.ted.cockpit.room.service.RoomService;

@RestController
public class RoomResource {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private DiscoveryClient discoveryClient; 
	
	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	public HttpEntity<List<Room>> getAllRooms(){
		List<Room> rooms = roomService.findAll();
		for(Room room: rooms){
		    List<String> expandedDeskIds = new ArrayList<>();
		    for(String deskId : room.getDeskIds()){
		        expandedDeskIds.add(expandDeskId(deskId));
		    }
		    room.setDeskIds(expandedDeskIds);
		}
        return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rooms", method = RequestMethod.PUT)
	public HttpEntity<Void> updateRoom(@RequestBody Room room){
	    List<String> croppedDeskIds = new ArrayList<>();
	    for(String deskId : room.getDeskIds()){
	        croppedDeskIds.add(cropDeskId(deskId));
	    }
	    room.setDeskIds(croppedDeskIds);
		roomService.updateRoom(room);
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rooms", method = RequestMethod.POST)
	public HttpEntity<Void> createRoom(@RequestBody Room room){
	    List<String> croppedIds = new ArrayList<>();
	    for(String deskId: room.getDeskIds()){
	        croppedIds.add(cropDeskId(deskId));
	    }
	    room.setDeskIds(croppedIds);
		roomService.createRoom(room);
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rooms", method = RequestMethod.DELETE)
	public HttpEntity<Void> deleteRoom(@RequestBody Room room){
	    List<String> croppedDeskIds = new ArrayList<>();
        for(String deskId : room.getDeskIds()){
            croppedDeskIds.add(cropDeskId(deskId));
        }
        room.setDeskIds(croppedDeskIds);
		roomService.deleteRoom(room);
        return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/rooms/{id}", method = RequestMethod.GET)
	public HttpEntity<Room> getRoom(@PathVariable String id){
		Room room = roomService.findById(id);
		List<String> expandedDeskIds = new ArrayList<>();
        for(String deskId : room.getDeskIds()){
            expandedDeskIds.add(expandDeskId(deskId));
        }
        room.setDeskIds(expandedDeskIds);
        return new ResponseEntity<Room>(room, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rooms/buildings/{buildingName}", method = RequestMethod.GET)
	public HttpEntity<List<Room>> getRoomByBuildingName(@PathVariable String buildingName){
		List<Room> rooms = roomService.findByBuildingName(buildingName);
		for(Room room : rooms){
		    List<String> expandedDeskIds = new ArrayList<>();
            for(String deskId : room.getDeskIds()){
                expandedDeskIds.add(expandDeskId(deskId));
            }
            room.setDeskIds(expandedDeskIds);
		}
        return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rooms/floors/{floorNumber}", method = RequestMethod.GET)
	public HttpEntity<List<Room>> getRoomByFloorNumber(@PathVariable int floorNumber){
		List<Room> rooms = roomService.findByFloorNumber(floorNumber);
		for(Room room : rooms){
		    List<String> expandedDeskIds = new ArrayList<>();
            for(String deskId : room.getDeskIds()){
                expandedDeskIds.add(expandDeskId(deskId));
            }
            room.setDeskIds(expandedDeskIds);
		}
        return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	private String expandDeskId(String deskId){
	    ServiceInstance zuul = discoveryClient.getInstances("zuul").get(0);
	    return zuul.getUri().toString() + deskId;
	}
	
	private String cropDeskId(String deskId){
        ServiceInstance zuul = discoveryClient.getInstances("zuul").get(0);
        return deskId.replaceAll(zuul.getUri().toString(), "");
    }
	
}
