package com.hcl.insurance.dto;

import lombok.Data;

@Data
public class TrendListDto {

	private Long policyId;
	
	private String policyName;
	
	private Integer count;
	
	private Long percentage;
	
}
