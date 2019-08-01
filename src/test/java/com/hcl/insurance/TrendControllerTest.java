package com.hcl.insurance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.insurance.controller.TrendController;
import com.hcl.insurance.dto.ResponseDto;
import com.hcl.insurance.dto.TrendListDto;
import com.hcl.insurance.dto.TrendsDto;
import com.hcl.insurance.service.TrendAnalysisService;

@RunWith(MockitoJUnitRunner.class)
public class TrendControllerTest {

	@InjectMocks
	TrendController trendController;
	
	@Mock
	TrendAnalysisService trendAnalysisService;
	
	List<TrendListDto> trendListDto = new ArrayList();
	TrendsDto trends = new TrendsDto();
	
	@Before
	public void setUp()
	{
		TrendListDto sample = new TrendListDto();
		sample.setCount(1);
		sample.setPercentage(20L);
		sample.setPolicyId(1L);
		sample.setPolicyName("Test");
		
		trends.setTotalCount(1L);
		trendListDto.add(sample);
		trends.setTrends(trendListDto);
		
	}
	
	@Test
	public void getTrendsTest() {
		
		Mockito.when(trendAnalysisService.getTrends(Matchers.anyString())).thenReturn(trends);
		ResponseEntity<ResponseDto> data = trendController.getTrends(Matchers.anyString());
		assertNotNull(data);
	}
	
}
