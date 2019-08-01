package com.hcl.insurance.service;

import org.springframework.stereotype.Component;

import com.hcl.insurance.dto.ResponseDto;
import com.hcl.insurance.exception.ResourceNotFoundException;

@Component
public interface GetPolicyService {
	
	
	public ResponseDto getPolicyList() throws ResourceNotFoundException;

	public ResponseDto getPolicyDetail(Long policyId) throws ResourceNotFoundException;

}
