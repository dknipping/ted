package com.ted.cockpit.room;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.ted.cockpit.room.entity.Room;
import com.ted.cockpit.room.service.RoomService;

@SpringBootApplication
@EnableEurekaClient
public class Application {
	
    @Autowired
    private RoomService roomService;
    
    public static void main(String[] args) {
		SpringApplication.run(RoomConfiguration.class, args);
	}
	
	@PostConstruct
	public void createRooms(){
	    Room room = new Room();
	    room.setBuildingName("Düsseldorf Office");
	    room.setFloorNumber(3);
	    room.setId("meetingRoom");
	    List<String> deskIds = new ArrayList<>();
	    deskIds.add("/desk/desks/001");
	    deskIds.add("/desk/desks/002");
	    deskIds.add("/desk/desks/003");
	    deskIds.add("/desk/desks/004");
	    room.setDeskIds(deskIds);
	    
	    roomService.createRoom(room);
	    
	    room.setBuildingName("Eschborn Office");
	    room.setFloorNumber(4);
        room.setId("workRoom001");
        deskIds = new ArrayList<>();
        deskIds.add("/desk/desks/021");
        deskIds.add("/desk/desks/022");
        deskIds.add("/desk/desks/023");
        room.setDeskIds(deskIds);
        
        roomService.createRoom(room);
	}
}
