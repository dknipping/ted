package com.ted.cockpit.desk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ted.cockpit.desk.entity.Desk;
import com.ted.cockpit.desk.repository.DeskRepository;

@Service
public class DeskServiceImpl implements DeskService {
	
	@Autowired
	private DeskRepository deskRepository;

	@Override
	public Desk createDesk(Desk desk) {
		return deskRepository.save(desk);
	}

	@Override
	public Desk findDeskByNumber(Integer number) {
		return deskRepository.findByDeskNumber(number);
	}

	@Override
	public void deleteDesk(Long id) {
		deskRepository.delete(id);
	}

}
