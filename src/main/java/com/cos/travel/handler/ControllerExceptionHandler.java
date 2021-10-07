package com.cos.travel.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.travel.handler.ex.CustomUpdateValidationException;
import com.cos.travel.handler.ex.CustomValidationException;
import com.cos.travel.util.Script;
import com.cos.travel.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		return Script.back(e.getErrorMap().toString());
	}
	
	@ExceptionHandler(CustomUpdateValidationException.class)
	public ResponseEntity<?> validationApiException(CustomUpdateValidationException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}
}
