package com.hcl.insurance.dto;

import lombok.Data;

@Data
public class PurchasePolicyDto {
	
	private Long userId;
	
	private Long policyId;
	
	private boolean termAndCondition;
	
	private String nominee;

}
