package com.hcl.insurance.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.insurance.dto.TrendListDto;
import com.hcl.insurance.dto.TrendsDto;
import com.hcl.insurance.entity.Policy;
import com.hcl.insurance.repository.CustomerPolicyRepository;
import com.hcl.insurance.repository.PolicyRepository;

@Service
public class TrendAnalysisService {

	@Autowired
	private CustomerPolicyRepository customerPolicyRepository;

	@Autowired
	private PolicyRepository policyRepository;

	public TrendsDto getTrends(String analysisType) {

		LocalDate startDate = getStartDate(analysisType);
		TrendsDto trends = new TrendsDto();

		Long count = customerPolicyRepository.countTotalPoliciesSold(Date.valueOf(startDate),
				Date.valueOf(LocalDate.now()));
		List<Object[]> data = customerPolicyRepository.countPerPolicySold(Date.valueOf(startDate),
				Date.valueOf(LocalDate.now()));

		trends.setTotalCount(count);
		List<TrendListDto> trendList = new ArrayList();
		for (Object[] object : data) {
			TrendListDto trend = new TrendListDto();
			Long policyId = Long.valueOf(object[0].toString());
			Optional<Policy> optional = policyRepository.findById(policyId);

			if (optional.isPresent()) {
				Policy policy = optional.get();
				trend.setPolicyId(policy.getPolicyId());
				trend.setPolicyName(policy.getPolicyName());
				trend.setCount(Integer.valueOf(object[1].toString()));
				trend.setPercentage((trend.getCount() * 100) / count);
			}
			trendList.add(trend);
		}

		trends.setTrends(trendList);
		return trends;
	}

	private LocalDate getStartDate(String analysisType) {

		if ("Weekly".equalsIgnoreCase(analysisType)) {
			return LocalDate.now().minusDays(7L);
		} else if ("Monthly".equalsIgnoreCase(analysisType)) {
			return LocalDate.now().minusMonths(1L);
		}
		return LocalDate.now().minusYears(10L);
	}

}
