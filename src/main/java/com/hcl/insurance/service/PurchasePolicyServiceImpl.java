package com.hcl.insurance.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.insurance.dto.PurchasePolicyDto;
import com.hcl.insurance.dto.ResponseDto;
import com.hcl.insurance.entity.Customer;
import com.hcl.insurance.entity.CustomerPolicy;
import com.hcl.insurance.entity.Policy;
import com.hcl.insurance.exception.InvalidInputException;
import com.hcl.insurance.repository.CustomerPolicyRepository;
import com.hcl.insurance.repository.CustomerRepository;
import com.hcl.insurance.repository.PolicyRepository;

@Service
public class PurchasePolicyServiceImpl implements PurchasePolicyService {

	@Autowired
	private CustomerPolicyRepository customerPolicyRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PolicyRepository policyRepository;

	@Override
	public ResponseDto purchasePolicy(PurchasePolicyDto purchasePolicyDto) throws InvalidInputException {
		ResponseDto responseDto = new ResponseDto();
		if (purchasePolicyDto.isTermAndCondition() && validateInputs(purchasePolicyDto)) {

			Optional<Customer> customerOptional = customerRepository.findById(purchasePolicyDto.getCustomerId());
			Optional<Policy> policyOptional = policyRepository.findById(purchasePolicyDto.getPolicyId());

			if (customerOptional.isPresent() && policyOptional.isPresent()
					&& validateCustomerDetails(customerOptional.get(), policyOptional.get(), purchasePolicyDto)) {
				CustomerPolicy customerPolicy = new CustomerPolicy();
				customerPolicy.setCustomerId(customerOptional.get());
				customerPolicy.setPolicyId(policyOptional.get());
				customerPolicy.setPolicyPurchaseDate(LocalDate.now());
				customerPolicy.setPolicyMaturityDate(LocalDate.now().plusYears(policyOptional.get().getPolicyPeriod()));
				CustomerPolicy saveCustomer = customerPolicyRepository.save(customerPolicy);
				responseDto.setData(saveCustomer);
				responseDto.setHttpStatus(HttpStatus.CREATED);
				responseDto.setMessage("Policy Registered Successfully");
				
				return responseDto;
				
			} else {
				responseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
				responseDto.setMessage(
						"Either Customer Id or Policy Id dose not exists. Please check and resubmit the request");
				return responseDto;
			}

		} else {
			responseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
			responseDto.setMessage("Please read and accept mentioned T&C to purchase Policy");
			return responseDto;
		}
		
	}

	private boolean validateInputs(PurchasePolicyDto purchasePolicyDto) throws InvalidInputException {
		if (null == purchasePolicyDto.getCustomerId()) {
			throw new InvalidInputException("Customer Id should not be empty");
		} else if (null == purchasePolicyDto.getPolicyId()) {
			throw new InvalidInputException("Policy Id should not be empty");
		}
		return true;
	}

	private boolean validateCustomerDetails(Customer customer, Policy policy, PurchasePolicyDto purchasePolicyDto)
			throws InvalidInputException {
		int customerAge = Period.between(customer.getDob(), LocalDate.now()).getYears();
		if (customerAge < 13 && StringUtils.isEmpty(purchasePolicyDto.getNominee())) {
			throw new InvalidInputException(
					"Nominee should not be empty if Age of policy Holder is less than 13 years");
		} else if (customerAge < policy.getPolicyFromAge() || customerAge > policy.getPolicyToAge()) {
			throw new InvalidInputException(
					"Sorry you are not eligible for selected policy. Please read description properly");
		}
		return true;
	}

}
