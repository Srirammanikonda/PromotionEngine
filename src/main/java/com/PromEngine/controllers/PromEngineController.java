package com.PromEngine.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PromEngineController {
	
	@RequestMapping(value="/calculateFinalPrice", method=RequestMethod.POST)
	public ResponseEntity<String> calculateFinalPrice() {
		
		return ResponseEntity.ok().body("The Total Price is: ");
	}
}
