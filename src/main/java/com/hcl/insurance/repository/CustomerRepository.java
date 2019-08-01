package com.hcl.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.insurance.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
