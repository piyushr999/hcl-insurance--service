package com.hcl.insurance.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData implements Serializable {

	private static final long serialVersionUID = 4806995052658367581L;

	private String message;
	private HttpStatus httpStatus;
	private transient Object data;

}
