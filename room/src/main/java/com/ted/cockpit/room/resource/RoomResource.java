package com.ted.cockpit.room.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	public HttpEntity<List<Room>> getAllRooms(){
		List<Room> rooms = roomService.findAll();
        return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rooms", method = RequestMethod.PUT)
	public HttpEntity<Void> updateRoom(@RequestBody Room room){
		roomService.updateRoom(room);
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rooms", method = RequestMethod.POST)
	public HttpEntity<Void> createRoom(@RequestBody Room room){
		roomService.createRoom(room);
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rooms", method = RequestMethod.DELETE)
	public HttpEntity<Void> deleteRoom(@RequestBody Room room){
		roomService.deleteRoom(room);
        return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/rooms/{id}", method = RequestMethod.GET)
	public HttpEntity<Room> getRoom(@PathVariable String id){
		Room room = roomService.findById(id);
        return new ResponseEntity<Room>(room, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rooms/buildings/{buildingName}", method = RequestMethod.GET)
	public HttpEntity<List<Room>> getRoomByBuildingName(@PathVariable String buildingName){
		List<Room> rooms = roomService.findByBuildingName(buildingName);
        return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rooms/floors/{floorNumber}", method = RequestMethod.GET)
	public HttpEntity<List<Room>> getRoomByFloorNumber(@PathVariable int floorNumber){
		List<Room> room = roomService.findByFloorNumber(floorNumber);
        return new ResponseEntity<List<Room>>(room, HttpStatus.OK);
	}
	
}
