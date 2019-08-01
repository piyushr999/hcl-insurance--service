package com.hcl.insurance.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_policy_id")
	private Long customerPolicyId;
	
	@Column(name="policy_id")
	private Long policyId;
	
	@Column(name="customer_id")
	private Long customerId;
	
	@Column(name="policy_purchase_date")
	private Date policyPurchaseDate;
	
	@Column(name="policy_maturity_date")
	private Date policyMaturityDate;
	
}