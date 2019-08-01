package com.hcl.insurance.service;

import javax.activity.InvalidActivityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.insurance.dto.PurchasePolicyDto;
import com.hcl.insurance.dto.ResponseDto;
import com.hcl.insurance.entity.CustomerPolicy;
import com.hcl.insurance.repository.CustomerPolicyRepository;

@Service
public class PurchasePolicyServiceImpl implements PurchasePolicyService {

	@Autowired
	private CustomerPolicyRepository customerPolicyRepository;

	@Override
	public ResponseDto purchasePolicy(PurchasePolicyDto purchasePolicyDto) throws InvalidActivityException {
		ResponseDto responseDto = new ResponseDto();
		if (purchasePolicyDto.isTermAndCondition() && validateInputs(purchasePolicyDto)) {
			
			

			CustomerPolicy customerPolicy = new CustomerPolicy();
			customerPolicyRepository.save(customerPolicy);

		} else {
			responseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
			responseDto.setMessage("Please read and accept mentioned T&C to purchase Policy");
		}
		return responseDto;
	}

	private boolean validateInputs(PurchasePolicyDto purchasePolicyDto) throws InvalidActivityException {
		if (null == purchasePolicyDto.getCustomerId()) {
			throw new InvalidActivityException("Customer Id should not be empty");
		} else if (null == purchasePolicyDto.getPolicyId()) {
			throw new InvalidActivityException("Policy Id should not be empty");
		}
		return true;
	}

}
