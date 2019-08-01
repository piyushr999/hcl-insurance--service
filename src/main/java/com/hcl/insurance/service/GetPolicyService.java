package com.hcl.insurance.service;

import org.springframework.stereotype.Component;

import com.hcl.insurance.dto.ResponseDto;

@Component
public interface GetPolicyService {
	
	
	public ResponseDto getPolicyList();

	public ResponseDto getPolicyDetail(Long policyId);

}
