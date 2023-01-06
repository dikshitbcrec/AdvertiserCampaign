package com.adtech.controller;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.adtech.exception.AdNotFoundException;
import com.adtech.exception.ProductNotFoundException;
import com.adtech.exception.FileExistingException;
import com.adtech.exception.FileNotFoundException;
import com.adtech.model.ErrorModel;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
//	
//	@Override
//	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//	  Map<String, String> errors = new HashMap<>();
//	  ex.getBindingResult().getAllErrors().forEach((error) ->{
//	  String fieldName = ((FieldError) error).getField();
//	  String message = error.getDefaultMessage();
//	  errors.put(fieldName, message);
//	  });
//
//	     return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
//
//	   }
	
	
	
	
	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorModel> handleproductNotFoundException(ProductNotFoundException ex, WebRequest req)
	{
		ErrorModel model = new ErrorModel(new Date(), ex.getMessage(),HttpStatus.NOT_FOUND.value(),req.getDescription(false));
		return new ResponseEntity<ErrorModel>(model,HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(AdNotFoundException.class)
	public ResponseEntity<ErrorModel> handleIdNotFoundException(AdNotFoundException ex,WebRequest req)
	{
		ErrorModel model = new ErrorModel(new Date(), ex.getMessage(),HttpStatus.NOT_FOUND.value(),req.getDescription(false));
		return new ResponseEntity<ErrorModel>(model,HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<ErrorModel> imageNotFound(FileNotFoundException ex,WebRequest req)
	{
		ErrorModel model=new ErrorModel(new Date(),ex.getMessage(),HttpStatus.NOT_FOUND.value(),req.getDescription(false));
		return new ResponseEntity<ErrorModel>(model,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FileExistingException.class)
	public ResponseEntity<ErrorModel> handleFilenotFound(FileExistingException ex,WebRequest req){
		ErrorModel model=new ErrorModel(new Date(),ex.getMessage(),HttpStatus.CONFLICT.value(),req.getDescription(false));
		return new ResponseEntity<ErrorModel>(model,HttpStatus.CONFLICT);
	}
}
