package com.hcl.insurance.service;

import com.hcl.insurance.dto.CustomerDto;
import com.hcl.insurance.entity.Customer;

public interface CustomerService {
	
	public CustomerDto getCustomer(Long id);

}
