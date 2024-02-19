package com.pgobi.rewardingpoints.exception;

import com.pgobi.rewardingpoints.model.ErrorResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(RewardingPointsException.class)
	public ResponseEntity<ErrorResponse> otherExceptionHandler(RewardingPointsException exc) {
		return ResponseEntity
				.status(exc.getHttpStatus())
				.body(new ErrorResponse(false, exc.getHttpStatus(), exc.getMessage()));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> badrequestRuntimeException(RuntimeException exception) {
		logger.error("[RestExceptionHandler] badrequestRuntimeException: " +exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

}
