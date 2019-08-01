package com.hcl.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.insurance.exception.ResourceNotFoundException;
import com.hcl.insurance.service.GetPolicyService;

@CrossOrigin("*")
@RestController
@RequestMapping("/polices")
public class GetAllPoliciesController {
	
	@Autowired
	GetPolicyService getPolicyService;

	@GetMapping("/")
	public ResponseEntity<Object> getPolicyList()throws ResourceNotFoundException{
		return new ResponseEntity<>(getPolicyService.getPolicyList(),HttpStatus.OK);
	}
	
	@GetMapping("/{policyId}")
	public ResponseEntity<Object> getPolicyDetails(@PathVariable("policyId") Long policyId)throws ResourceNotFoundException{
		return new ResponseEntity<>(getPolicyService.getPolicyDetail(policyId),HttpStatus.OK);
	}
}
