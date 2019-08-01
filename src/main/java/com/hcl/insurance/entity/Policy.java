package com.hcl.insurance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "policy")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "policyId")
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "policy_id")
	private Long policyId;

	@Column(name = "policy_name")
	private String policyName;

	@Column(name = "policy_period")
	private Integer policyPeriod;

	@Column(name = "policy_from_age")
	private Integer policyFromAge;

	@Column(name = "policy_to_age")
	private Integer policyToAge;

	@Column(name = "policy_base_amount")
	private Double policyBaseAmount;

	private String policyDescription;
	private String policyTermsAndCondition;

}
