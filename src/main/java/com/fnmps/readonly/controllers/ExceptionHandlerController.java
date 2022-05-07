package com.fnmps.readonly.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	class UnprocessableError {
		private String message;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		if(ex.getRootCause() instanceof UnrecognizedPropertyException) {
			UnrecognizedPropertyException e = (UnrecognizedPropertyException) ex.getRootCause();
			UnprocessableError unprocessableError = new UnprocessableError();
			unprocessableError.setMessage("Property not recognized: " + e.getPropertyName());
			return new ResponseEntity<>(unprocessableError,HttpStatus.UNPROCESSABLE_ENTITY);
		} else if(ex.getRootCause() instanceof IgnoredPropertyException) {
			IgnoredPropertyException e = (IgnoredPropertyException) ex.getRootCause();
			UnprocessableError unprocessableError = new UnprocessableError();
			unprocessableError.setMessage("Invalid property: " + e.getPropertyName());
			return new ResponseEntity<>(unprocessableError,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return super.handleHttpMessageNotReadable(ex, headers, status, request);
	}

}
