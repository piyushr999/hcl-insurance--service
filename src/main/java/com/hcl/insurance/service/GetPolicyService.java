package com.hcl.insurance.service;

import org.springframework.stereotype.Component;

import com.hcl.insurance.dto.ResponseData;

@Component
public interface GetPolicyService {
	
	
	public ResponseData getPolicyList();

	public ResponseData getPolicyDetail();

}
