package com.hcl.insurance.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.insurance.entity.CustomerPolicy;

@Repository
public interface CustomerPolicyRepository extends JpaRepository<CustomerPolicy, Long> {

	@Query(value = " select count(policy_id) from customer_policy where policy_purchase_date between :startDate and :now " , nativeQuery = true)
	public Long countTotalPoliciesSold(Date startDate, Date now);

	@Query(value = " select policy_id,count(policy_id) from customer_policy where policy_purchase_date between :startDate and :now group by policy_id ", nativeQuery = true)
	public List<Object[]> countPerPolicySold(Date startDate, Date now);

}
