package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;






@ControllerAdvice
public class ExpenseExceptionController {
	
	
	@ExceptionHandler(value = ExpenseNotFoundException.class)
		public ResponseEntity<Object> exception(ExpenseNotFoundException exception) {
			return new ResponseEntity<>("Expense not found", HttpStatus.NOT_FOUND);
		}
	

}
