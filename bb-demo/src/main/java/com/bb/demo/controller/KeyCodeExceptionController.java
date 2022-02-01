package com.bb.demo.controller;


import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class KeyCodeExceptionController {
	
   @ExceptionHandler(value = EntityNotFoundException.class)
   public ResponseEntity<Object> exception(EntityNotFoundException exception) {
      return new ResponseEntity<>("KeyCode data not found", HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler(value = IllegalArgumentException.class)
   public ResponseEntity<Object> exception(IllegalArgumentException exception) {
      return new ResponseEntity<>("KeyCode data not saved due to invalid data", HttpStatus.BAD_REQUEST);
   }
   
   @ExceptionHandler(value = Exception.class)
   public ResponseEntity<Object> exception(Exception exception) {
      return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
}