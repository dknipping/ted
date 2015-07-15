package com.ted.cockpit.desk.resource;

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

import com.ted.cockpit.desk.entity.Desk;
import com.ted.cockpit.desk.service.DeskService;

@RestController
public class DeskResource {

	@Autowired
	private DeskService deskService;
	
	@RequestMapping(value = "/desks/{deskNumber}", method = RequestMethod.GET)
    public HttpEntity<Desk> findDesk(@PathVariable Integer deskNumber) {
		Desk desk = deskService.findDesk(deskNumber);
        return new ResponseEntity<Desk>(desk, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/desks", method = RequestMethod.POST)
    public HttpEntity<Desk> createDesk(@RequestBody Desk desk) {
		
		Desk currentDesk = deskService.findDesk(desk.getDeskNumber());
		
		if (currentDesk == null) {
			
			Desk createdDesk = deskService.createDesk(desk);
			
			return new ResponseEntity<Desk>(createdDesk, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<Desk>(currentDesk, HttpStatus.NOT_MODIFIED);
    }
	
	@RequestMapping(value = "/desks/{deskNumber}", method = RequestMethod.DELETE)
    public HttpEntity<Void> deleteDesk(@PathVariable Integer deskNumber) {
		
		Desk desk = deskService.findDesk(deskNumber);
		
		if (desk == null) {
			
			return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
		}
		
		deskService.deleteDesk(desk);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value ="/desks" , method = RequestMethod.GET)
	public HttpEntity<List<Desk>> findAllDesks() {
		List<Desk> allDesks = deskService.findAllDesks();
		return new ResponseEntity<List<Desk>>(allDesks, HttpStatus.OK);
	}

}
