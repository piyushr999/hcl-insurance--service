package com.hcl.insurance.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_policy")
public class CustomerPolicy {

	@Id
	private Integer customerPolicyId;
	private Integer policyId;
	private Integer customerId;
	private Date policyPurchaseDate;
	private Date policyMaturityDate;
	
}