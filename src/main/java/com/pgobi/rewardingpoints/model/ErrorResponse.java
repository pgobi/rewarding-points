package com.pgobi.rewardingpoints.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class ErrorResponse {
	private boolean result;
	private HttpStatus code;
	private String message;
}