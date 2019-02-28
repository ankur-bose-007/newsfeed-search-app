package com.newsfeedback.core.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.newsfeedback.core.service.UserDetailsServiceImpl;

/**
 * @author 729706
 * Name: Ankur Bose
 * Date: Feb 28, 2019
 */
public class JwtAuthTokenFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		logger.info("inside JwtAuthTokenFilter");
		try {
			String token = getJwt(request);
			if (token != null && jwtProvider.validateJwtToken(token)) {
				String username = jwtProvider.getUserNameFromJwtToken(token);

				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails.getUsername(), null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		} catch (Exception e) {
			logger.info("Could not set Token -> Message: {}", e);
		}
		chain.doFilter(request, response);
	}

	private String getJwt(HttpServletRequest request) {
		logger.info("inside getJwt");
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.contains("Bearer")) {
			return authHeader.replace("Bearer ", "");
		}
		logger.info("leaving getJwt");
		return null;
	}
}
