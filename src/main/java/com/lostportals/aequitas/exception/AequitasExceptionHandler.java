package com.lostportals.aequitas.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice  
@RestController 
public class AequitasExceptionHandler {
	static final Logger log = LogManager.getLogger();

	@ExceptionHandler(value = Exception.class)
	public String handleException(Exception e) throws Exception {
		log.error(e + " : " + e.getMessage(), e);
		throw e;
	}
}
