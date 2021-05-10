package com.nse.webchat.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nse.webchat.utility.ChatUserConstants;



@ControllerAdvice
class AppExceptionHandler {

	@ExceptionHandler(ChatUserValidationException.class)
	public ResponseEntity<Object> customUserValidationErrorHandler(HttpServletRequest req, Exception e) {

		Map<String, Object> response = new HashMap<String, Object>();
		response.put(ChatUserConstants.Response.ERROR, true);
		response.put(ChatUserConstants.Response.MESSAGE, e.getMessage());
		response.put(ChatUserConstants.Response.URL, req.getRequestURL());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ChatUserNotFoundException.class)
	public ResponseEntity<Object> customUserNotFoundErrorHandler(HttpServletRequest req, Exception e) {

		Map<String, Object> response = new HashMap<String, Object>();
		response.put(ChatUserConstants.Response.ERROR, true);
		response.put(ChatUserConstants.Response.MESSAGE, e.getMessage());
		response.put(ChatUserConstants.Response.URL, req.getRequestURL());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ChatUserBadCredentialException.class)
	public ResponseEntity<Object> customChatUserBadCredentialErrorHandler(HttpServletRequest req, Exception e) {

		Map<String, Object> response = new HashMap<String, Object>();
		response.put(ChatUserConstants.Response.ERROR, true);
		response.put(ChatUserConstants.Response.MESSAGE, e.getMessage());
		response.put(ChatUserConstants.Response.URL, req.getRequestURL());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ChatUserDisabledException.class)
	public ResponseEntity<Object> customChatUserDisabledErrorHandler(HttpServletRequest req, Exception e) {

		Map<String, Object> response = new HashMap<String, Object>();
		response.put(ChatUserConstants.Response.ERROR, true);
		response.put(ChatUserConstants.Response.MESSAGE, e.getMessage());
		response.put(ChatUserConstants.Response.URL, req.getRequestURL());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> defaultErrorHandler(HttpServletRequest req, Exception e) {

		Map<String, Object> response = new HashMap<String, Object>();
		response.put(ChatUserConstants.Response.ERROR, true);
		response.put(ChatUserConstants.Response.MESSAGE, e.getMessage());
		response.put(ChatUserConstants.Response.URL, req.getRequestURL());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MemberAlreadyExistsException.class)
	public ResponseEntity<String> memberAlreadyExists(MemberAlreadyExistsException ex){
		
		return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MemberNotExistsException.class)
	public ResponseEntity<String> memberNotExists(MemberNotExistsException ex){
		
		return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(GroupAlreadyExistsException.class)
	public ResponseEntity<String> groupAlreadyExists(GroupAlreadyExistsException ex){
		
		return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
	}
}
