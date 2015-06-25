package com.ted.cockpit.room.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ted.cockpit.room.entity.Room;

public interface RoomRepository extends JpaRepository<Room, String> {

	List<Room> findByFloorNumber(int floorNumber);

	List<Room> findByBuildingName(String buildingName);
	
	Room findById(String id);

}
