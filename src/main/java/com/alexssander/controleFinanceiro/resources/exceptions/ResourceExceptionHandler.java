package com.alexssander.controleFinanceiro.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.alexssander.controleFinanceiro.services.exceptions.ObjectNotFoundException;


@ControllerAdvice
public class ResourceExceptionHandler {
	
	
   @ExceptionHandler(ObjectNotFoundException.class)
   public ResponseEntity<StandardError> objectNotFoundException (ObjectNotFoundException ex , HttpServletRequest request ){
	
	   StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	   
	   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	   
   }

}
