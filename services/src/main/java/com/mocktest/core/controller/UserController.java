package com.mocktest.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mocktest.core.entity.User;
import com.mocktest.core.security.JwtProvider;
import com.mocktest.core.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	@Autowired
	private JwtProvider jwtProvider;
	
	@GetMapping("test")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String test(){
		return "inside test";
	}
	
	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody User user){
		
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				user.getEmailId(),
				user.getPassword()
				)
			);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		 
        String jwt = jwtProvider.generateJwtToken(authentication);
        return new ResponseEntity<String>(jwt,HttpStatus.OK);
	}
	
	@PostMapping("signup")
	public ResponseEntity<String> signup(@RequestBody User user){
		if(user==null)
			return new ResponseEntity<String>("User null",HttpStatus.BAD_REQUEST);
		if(userService.saveUser(user))
			return new ResponseEntity<String>("User Registered Successfully",HttpStatus.CREATED);
		return new ResponseEntity<String>("User Already Exists",HttpStatus.CONFLICT);
	}
}
