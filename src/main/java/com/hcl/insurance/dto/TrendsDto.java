package com.hcl.insurance.dto;

import java.util.List;

import lombok.Data;
@Data
public class TrendsDto {

	private Long totalCount;
	
	List<TrendListDto> trends;
	
}
