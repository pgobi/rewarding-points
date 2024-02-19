package com.pgobi.rewardingpoints.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RewardingPointsException extends RuntimeException{

    private final HttpStatus httpStatus;
    public RewardingPointsException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
