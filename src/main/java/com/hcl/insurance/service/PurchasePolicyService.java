package com.hcl.insurance.service;

import com.hcl.insurance.dto.PurchasePolicyDto;
import com.hcl.insurance.dto.ResponseDto;
import com.hcl.insurance.exception.InvalidInputException;

public interface PurchasePolicyService {

	public ResponseDto purchasePolicy(PurchasePolicyDto purchasePolicyDto) throws	 InvalidInputException;

}
