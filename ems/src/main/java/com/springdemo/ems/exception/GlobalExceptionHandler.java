package com.springdemo.ems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	//add exception handling code here
		//Add an exception handler using @ExceptionHandler
			@ExceptionHandler
			public ResponseEntity<EmployeeCustomErrorResponse> handleException(EmployeeNotFoundException ex){
				
				//create a CustomerCustomErrorResponse object
				EmployeeCustomErrorResponse obj = new EmployeeCustomErrorResponse();
				obj.setStatus(HttpStatus.NOT_FOUND.value());
				obj.setMessage(ex.getMessage());
				obj.setTimeStamp(System.currentTimeMillis());
				
				//return ResponseEntity
				return new ResponseEntity<>(obj,HttpStatus.NOT_FOUND);
				
			}
			
			//add another exception hanlder--to catch any exception
			@ExceptionHandler
			public ResponseEntity<EmployeeCustomErrorResponse> handleException(Exception ex){
						//create a CustomerCustomErrorResponse object
				EmployeeCustomErrorResponse obj = new EmployeeCustomErrorResponse();
						obj.setStatus(HttpStatus.BAD_REQUEST.value());
						obj.setMessage(ex.getMessage());
						obj.setTimeStamp(System.currentTimeMillis());
						
						//return ResponseEntity
						return new ResponseEntity<>(obj,HttpStatus.BAD_REQUEST);
			}
}
