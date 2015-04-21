package com.ted.cockpit.desk.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ted.cockpit.desk.entity.Desk;
import com.ted.cockpit.desk.service.DeskService;

@RestController
public class DeskResource {

	@Autowired
	private DeskService deskService;
	
	@RequestMapping(value = "/desk", method = RequestMethod.GET)
    public HttpEntity<Desk> findDesk(@RequestParam Integer deskNumber) {
		Desk desk = deskService.findDeskByNumber(deskNumber);
        return new ResponseEntity<Desk>(desk, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/desk", method = RequestMethod.POST)
    public HttpEntity<Desk> createDesk(@RequestBody Desk desk) {
		Desk createdDesk = deskService.createDesk(desk);
        return new ResponseEntity<Desk>(createdDesk, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/desk/{id}", method = RequestMethod.DELETE)
    public HttpEntity<Void> deleteDesk(@PathVariable Long id) {
		deskService.deleteDesk(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
