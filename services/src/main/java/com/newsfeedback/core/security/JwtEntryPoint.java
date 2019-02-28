package com.newsfeedback.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author 729706
 * Name: Ankur Bose
 * Date: Feb 28, 2019
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
	private static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
			throws IOException, ServletException {
		logger.error("Error message->{}", ex.getMessage());
		if (ex instanceof AuthenticationException)
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong credentials");
	}
}
