package com.hcl.insurance;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.insurance.dto.TrendsDto;
import com.hcl.insurance.entity.Policy;
import com.hcl.insurance.repository.CustomerPolicyRepository;
import com.hcl.insurance.repository.PolicyRepository;
import com.hcl.insurance.service.TrendAnalysisService;


@RunWith(MockitoJUnitRunner.class)
public class TrendAnalysisServiceTest {

	@InjectMocks
	TrendAnalysisService trendAnalysisService;
	
	@Mock
	CustomerPolicyRepository customerPolicyRepository;
	

	@Mock
	PolicyRepository policyRepository;
	
	@Before
	public void setUp(){
		
		Policy policy = new Policy();
		policy.setPolicyId(1L);
		policy.setPolicyName("Test");
	}
	
	@Test
	public void getTrendsTest() {
		List<Object[]> objectList = null;
		Policy policy = new Policy();
		policy.setPolicyId(1L);
		policy.setPolicyName("Test");
		lenient().when(customerPolicyRepository.countTotalPoliciesSold(new Date(2019,04,01), new Date(2019,04,01))).thenReturn(1L);
		lenient().when(customerPolicyRepository.countPerPolicySold(new Date(2019,04,01), new Date(2019,04,01))).thenReturn(objectList);
		lenient().when(policyRepository.findById(1L)).thenReturn(Optional.of(policy));
		
		TrendsDto trends = trendAnalysisService.getTrends("all");
		assertNotNull(trends);
		
		
	}
}
