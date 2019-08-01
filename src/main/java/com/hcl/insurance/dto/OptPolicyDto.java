package com.hcl.insurance.dto;

import lombok.Data;

@Data
public class OptPolicyDto {
	
	private Long userId;
	
	private Long policyId;
	
	private boolean termAndCondition;
	
	private String nominee;

}
