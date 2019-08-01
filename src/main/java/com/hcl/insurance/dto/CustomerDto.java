package com.hcl.insurance.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class CustomerDto {
	private Long customerId;

	private String name;
	private String gender;
	private String email;
	private String address;
	private LocalDate dob;
	private String phoneNo;
	private String nomineeName;
	List<PolicyDto> policies;

}
