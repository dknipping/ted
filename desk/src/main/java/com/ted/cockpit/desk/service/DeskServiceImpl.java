package com.ted.cockpit.desk.service;

import java.util.List;

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
	public void deleteDesk(Desk desk) {
		
		deskRepository.delete(desk);
	}

	@Override
	public List<Desk> findAllDesks() {
		
		return deskRepository.findAll();
	}

	@Override
	public Desk findDesk(Integer deskNumber) {
		
		return deskRepository.findByDeskNumber(deskNumber);
	}

}
