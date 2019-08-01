package com.hcl.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.insurance.dto.ResponseDto;
import com.hcl.insurance.dto.TrendsDto;
import com.hcl.insurance.service.TrendAnalysisService;

@RestController
@RequestMapping("policies/analysis")
public class TrendController {

	@Autowired
	private TrendAnalysisService trendAnalysisService;

	
	@GetMapping
	public ResponseEntity<ResponseDto> getTrends(@RequestParam("analysisType") String analysisType){
		
		TrendsDto trends = trendAnalysisService.getTrends(analysisType);
		
		ResponseDto response = new ResponseDto("Trend List", HttpStatus.OK, trends );

		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}

}
