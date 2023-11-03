package com.dw.securityproject.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.dw.securityproject.common.dto.BaseResponse;
import com.dw.securityproject.common.status.ResultCode;

@ControllerAdvice
public class CustomExceptionHandler {
	
	protected ResponseEntity<BaseResponse<Map<String, String>>> 
		handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage != null ? errorMessage 
					: "Not Exception Message");
		});
		return ResponseEntity.badRequest().body(new BaseResponse<>(
				ResultCode.ERROR.name(),
				errors,
				ResultCode.ERROR.getMsg()
		));		
	}
}
















