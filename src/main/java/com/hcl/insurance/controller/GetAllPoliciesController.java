package com.hcl.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.insurance.service.GetPolicyService;

@RestController
@RequestMapping("/polices")
public class GetAllPoliciesController {
	
	@Autowired
	GetPolicyService getPolicyService;

	@GetMapping("/")
	public ResponseEntity<Object> getPolicyList(){
		return new ResponseEntity<>(getPolicyService.getPolicyList(),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Object> getPolicyDetails(@RequestParam Long policyId){
		return new ResponseEntity<>(getPolicyService.getPolicyDetail(),HttpStatus.OK);
	}
}
