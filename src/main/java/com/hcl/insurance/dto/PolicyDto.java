package com.hcl.insurance.dto;

import lombok.Data;

@Data
public class PolicyDto {
	
	private Long policyId;
	private String policyName;
	private Integer policyPeriod;
	private Integer policyFromAge;
	private Integer policyToAge;
	private Double policyBaseAmount;
	private String policyDescription;
}
