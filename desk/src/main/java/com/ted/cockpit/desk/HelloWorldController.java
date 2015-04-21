package com.ted.cockpit.desk;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping("/helloworld")
    public HttpEntity<String> helloWorld() {
        return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
    }

}
