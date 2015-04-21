package com.ted.cockpit.desk.service;

import com.ted.cockpit.desk.entity.Desk;

public interface DeskService {

	Desk createDesk(Desk desk);
	Desk findDeskByNumber(Integer number);
	void deleteDesk(Long id);
}
