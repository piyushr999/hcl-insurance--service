package com.hcl.insurance.dto;

import lombok.Data;

@Data
public class PurchasePolicyDto {
	
	private Long customerId;
	
	private Long policyId;
	
	private boolean termAndCondition;
	
	private String nominee;

}
