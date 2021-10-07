package com.PromEngine.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.PromEngine.model.CartItems;

@RestController
@RequestMapping("/")
public class PromEngineController {
	
	@RequestMapping(value="/calculateFinalPrice", method=RequestMethod.POST)
	public ResponseEntity<String> calculateFinalPrice(@RequestBody List<CartItems> cartItems) {
		
		return ResponseEntity.ok().body("The Total Price is: ");
	}
}
