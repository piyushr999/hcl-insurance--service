package com.hcl.insurance.controller;

import javax.activity.InvalidActivityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.insurance.dto.PurchasePolicyDto;
import com.hcl.insurance.dto.ResponseDto;
import com.hcl.insurance.service.PurchasePolicyService;

@RestController
@RequestMapping("/polices")
public class PurchasePolicyController {

	@Autowired
	private PurchasePolicyService purchasePolicyService;

	@PostMapping("/customer/select")
	public ResponseEntity<Object> purchagePolicy(PurchasePolicyDto purchasePolicyDto) throws InvalidActivityException {

		ResponseDto responseDto = purchasePolicyService.purchasePolicy(purchasePolicyDto);
		return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
	}
}
