package com.hcl.insurance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/polices")
public class PolicyController {

	@PostMapping("/opt")
	public ResponseEntity<Object> optedPolicy(){
		return null;
	}
}
