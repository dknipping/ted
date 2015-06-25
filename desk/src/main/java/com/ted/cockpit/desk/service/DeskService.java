package com.ted.cockpit.desk.service;

import java.util.List;

import com.ted.cockpit.desk.entity.Desk;

public interface DeskService {

	Desk createDesk(Desk desk);
	void deleteDesk(Desk desk);
	Desk findDesk(Integer deskNumber);
	List<Desk> findAllDesks();
}
