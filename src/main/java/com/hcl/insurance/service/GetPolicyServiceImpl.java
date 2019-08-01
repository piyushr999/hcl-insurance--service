package com.hcl.insurance.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.insurance.dto.PolicyDto;
import com.hcl.insurance.dto.ResponseDto;
import com.hcl.insurance.entity.Policy;
import com.hcl.insurance.exception.ResourceNotFoundException;
import com.hcl.insurance.repository.PolicyRepository;

@Service
public class GetPolicyServiceImpl implements GetPolicyService{
	
	
	@Autowired
	PolicyRepository policyRepository;
	
	@Override
	public ResponseDto getPolicyList() throws ResourceNotFoundException{
		
		List<Policy> policyList = policyRepository.findAll();
		List<PolicyDto> PolicyListModel = new ArrayList<>();
		if(policyList.isEmpty()){
			throw new ResourceNotFoundException("No policy available");
		}
		for (Policy policy : policyList) {
			PolicyDto policyModel = new PolicyDto();
			BeanUtils.copyProperties(policy, policyModel);
			PolicyListModel.add(policyModel);
		} 
		return new ResponseDto("List of Stocks",HttpStatus.ACCEPTED,PolicyListModel);
	}

	@Override
	public ResponseDto getPolicyDetail(Long policyId) throws ResourceNotFoundException {
		
		
		
		return new ResponseDto("List of Stocks",HttpStatus.ACCEPTED,policyRepository.findById(policyId).orElseThrow(()->new ResourceNotFoundException("No policy available")));
	}

}
