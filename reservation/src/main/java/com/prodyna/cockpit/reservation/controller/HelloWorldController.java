package com.prodyna.cockpit.reservation.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mfroehlich on 23.04.2015.
 */
@RestController
public class HelloWorldController {

    @RequestMapping(value = "/helloworld", method = RequestMethod.GET)
    public HttpEntity<String> sayHelloWorld() {
        return new ResponseEntity<>("Hello World!", HttpStatus.CREATED);
    }

}
