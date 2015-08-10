package com.ted.cockpit.room.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ted.cockpit.room.entity.Room;
import com.ted.cockpit.room.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomRepository roomRepository;
	
	@Override
	public void createRoom(Room room) {
		roomRepository.save(room);
	}

	@Override
	@HystrixCommand(fallbackMethod = "getRoomFallback")
	public Room getRoom(String id) {
		return roomRepository.findOne(id);
	}
	
	public Room getRoomFallback(String id) {
		return null;
	}

	@Override
	public void updateRoom(Room room) {
		roomRepository.save(room);
	}

	@Override
	public void deleteRoom(Room room) {
		roomRepository.delete(room);
	}

	@Override
	@HystrixCommand(fallbackMethod = "findAllFallback")
	public List<Room> findAll() {
		return roomRepository.findAll();
	}
	
	public List<Room> findAllFallback() {
		return new ArrayList<>();
	}

	@Override
	public List<Room> findByFloorNumber(int floorNumber) {
		return roomRepository.findByFloorNumber(floorNumber);
	}

	@Override
	public List<Room> findByBuildingName(String buildingName) {
		return roomRepository.findByBuildingName(buildingName);
	}

	@Override
	public Room findById(String id) {
		return roomRepository.findById(id);
	}


}
