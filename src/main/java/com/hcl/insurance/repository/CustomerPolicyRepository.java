package com.hcl.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.insurance.entity.CustomerPolicy;

public interface CustomerPolicyRepository extends JpaRepository<CustomerPolicy, Long> {

}
