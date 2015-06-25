package com.ted.cockpit.room.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ted.cockpit.room.entity.Room;

@Service
public interface RoomService {
	
	void createRoom(Room room);
	Room getRoom(String id);
	void updateRoom(Room room);
	void deleteRoom(Room room);
	
	List<Room> findAll();
	
	List<Room> findByFloorNumber(int floorNumber);
	
	List<Room> findByBuildingName(String buildingName);
	
	Room findById(String id);
	

}
