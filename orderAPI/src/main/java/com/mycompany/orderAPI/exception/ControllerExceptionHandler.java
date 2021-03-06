 package com.mycompany.orderAPI.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Component
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
	
	@ExceptionHandler
	public void handleOtherException(Exception e, 
			HttpServletResponse response) throws IOException {
		log.info(e.getMessage());
		e.printStackTrace();
		response.sendError(500);
	}
	
	@ExceptionHandler
	public void handleBadCredentialsException(BadCredentialsException e, 
			HttpServletResponse response) throws IOException {
		log.info(e.getMessage());
		response.sendError(401);
		//401 : 로그인시 에러
		//403 : 인증이 안됐거나, 권한이 부족할 경우
	}
}

