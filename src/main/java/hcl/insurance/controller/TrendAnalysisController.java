package hcl.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hcl.insurance.service.TrendAnalysisService;

@RestController
@RequestMapping("/policies")
public class TrendAnalysisController {

	@Autowired
	TrendAnalysisService trendSAnalysisService;
	
	@GetMapping("/trend/{trendType}")
	public void getPoliciesTrends(@PathVariable String trendType) {
		
	}
	
	
	
}
