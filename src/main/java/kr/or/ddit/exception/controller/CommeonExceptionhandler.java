package kr.or.ddit.exception.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommeonExceptionhandler {
	
	@ExceptionHandler({ArithmeticException.class})
	public String handleException() {
		return"exception";
	}

}
