package com.dex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dex.models.ResponseMessage;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	  public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
	    return ResponseEntity
	        .status(HttpStatus.EXPECTATION_FAILED)
	        .body(new ResponseMessage("One or more files are too large!"));
	  }
}