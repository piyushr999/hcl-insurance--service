package com.hcl.insurance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.insurance.dto.CustomerDto;
import com.hcl.insurance.dto.PolicyDto;
import com.hcl.insurance.entity.Customer;
import com.hcl.insurance.entity.CustomerPolicy;
import com.hcl.insurance.exception.ResourceNotFoundException;
import com.hcl.insurance.repository.CustomerRepository;

@Service
public class CustemerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public CustomerDto getCustomer(Long id) {
		Optional<Customer> custOptional = customerRepository.findById(id);
		if(custOptional.isPresent()) {
			CustomerDto custDto = new CustomerDto();
			Customer custEntity = custOptional.get();
			custDto.setCustomerId(custEntity.getCustomerId());
			custDto.setName(custEntity.getName());
			custDto.setAddress(custEntity.getAddress());
			custDto.setDob(custEntity.getDob());
			custDto.setEmail(custEntity.getEmail());
			custDto.setGender(custEntity.getGender());
			custDto.setNomineeName(custEntity.getNomineeName());
			custDto.setPhoneNo(custEntity.getPhoneNo());
			List<CustomerPolicy> policiesEntity = custEntity.getCustomerPolicy();
			
			if(null != policiesEntity && policiesEntity.size() > 0) {
				List<PolicyDto> policieList = new ArrayList<>();
				policiesEntity.forEach(p->{
					PolicyDto policyDto = new PolicyDto();
					policyDto.setPolicyId(p.getPolicyId().getPolicyId());
					policyDto.setPolicyName(p.getPolicyId().getPolicyName());
					policyDto.setPolicyBaseAmount(p.getPolicyId().getPolicyBaseAmount());
					policyDto.setPolicyFromAge(p.getPolicyId().getPolicyFromAge());
					policyDto.setPolicyToAge(p.getPolicyId().getPolicyToAge());
					policieList.add(policyDto);
				});
				
				
			}
			
			return custDto;
		}else {
			throw new ResourceNotFoundException("Requested user not found");
		}
	}

}
