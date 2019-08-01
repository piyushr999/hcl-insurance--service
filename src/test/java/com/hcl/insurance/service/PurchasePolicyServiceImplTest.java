package com.hcl.insurance.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.insurance.dto.PurchasePolicyDto;
import com.hcl.insurance.exception.InvalidInputException;
import com.hcl.insurance.repository.CustomerPolicyRepository;
import com.hcl.insurance.repository.CustomerRepository;
import com.hcl.insurance.repository.PolicyRepository;

@RunWith(MockitoJUnitRunner.class)
public class PurchasePolicyServiceImplTest {

	@InjectMocks
	private PurchasePolicyServiceImpl purchasePolicyServiceImpl;
	
	@Mock
	private CustomerPolicyRepository customerPolicyRepository;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private PolicyRepository policyRepository;
	
	private PurchasePolicyDto purchasePolicyDto;
	
	@Before
	public void setUp() {
		purchasePolicyDto = new PurchasePolicyDto();
		purchasePolicyDto.setTermAndCondition(true);
		purchasePolicyDto.setCustomerId(Long.valueOf(1));
		purchasePolicyDto.setPolicyId(Long.valueOf(1));
	}
	
	@Test
	public void testPurchasePolicyWhenTnCIsFalse() throws InvalidInputException {
		purchasePolicyDto.setTermAndCondition(false);
		assertNotNull(purchasePolicyServiceImpl.purchasePolicy(purchasePolicyDto));
	}
	
	@Test(expected = InvalidInputException.class)
	public void testPurchasePolicyCustomerIdIsNull() throws InvalidInputException {
		purchasePolicyDto.setCustomerId(null);
		purchasePolicyServiceImpl.purchasePolicy(purchasePolicyDto);
	}
	
	@Test(expected = InvalidInputException.class)
	public void testPurchasePolicyPolicyIdIsNull() throws InvalidInputException {
		purchasePolicyDto.setPolicyId(null);
		purchasePolicyServiceImpl.purchasePolicy(purchasePolicyDto);
	}
	
	@Test 
	public void testPurchasePolicyWhenCustomerNotPresent() throws InvalidInputException {
		when(policyRepository.findById(Long.valueOf(1))).thenReturn(Optional.empty());
		when(customerRepository.findById(Long.valueOf(1))).thenReturn(Optional.empty());
		purchasePolicyServiceImpl.purchasePolicy(purchasePolicyDto);
	}
}
