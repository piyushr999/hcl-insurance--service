package com.hcl.insurance.service;

import javax.activity.InvalidActivityException;

import com.hcl.insurance.dto.PurchasePolicyDto;
import com.hcl.insurance.dto.ResponseDto;

public interface PurchasePolicyService {

	public ResponseDto purchasePolicy(PurchasePolicyDto purchasePolicyDto) throws InvalidActivityException;

}
