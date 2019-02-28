package com.newsfeedback.core.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newsfeedback.core.entity.Search;
import com.newsfeedback.core.entity.User;
import com.newsfeedback.core.security.JwtProvider;
import com.newsfeedback.core.service.UserService;

/**
 * @author 729706
 * Name: Ankur Bose
 * Date: Feb 28, 2019
 */
@RestController
@RequestMapping("user")
public class UserController extends GlobalErrorHandlerController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;
	@Autowired
	private JwtProvider jwtProvider;

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("getUserDetails/{emailId}")
	public ResponseEntity<User> getUserDetails(@PathVariable("emailId") String emailId) {
		logger.info("inside getUserDetails");
		User user = null;
		if (emailId != null && !emailId.equals("")) {
			user = userService.getUserDetails(emailId);
			if (user != null){
				logger.info("leaving getUserDetails with status ok");
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		}
		logger.info("leaving getUserDetails");
		return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("login")
	public ResponseEntity<String> login(@Valid @RequestBody User user) {
		logger.info("inside login");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmailId(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		if (!userService.isActive(user)){
			logger.info("leaving login with status bad request");
			return new ResponseEntity<String>("User doesn't exists", HttpStatus.BAD_REQUEST);
		}
		String jwt = jwtProvider.generateJwtToken(authentication);
		logger.info("leaving login with status ok");
		return new ResponseEntity<String>(jwt, HttpStatus.OK);
	}

	@PostMapping("signup")
	public ResponseEntity<String> signup(@Valid @RequestBody User user) {
		logger.info("inside signup");
		if (user == null){
			logger.info("leaving signup with status bad request");
			return new ResponseEntity<String>("User null", HttpStatus.BAD_REQUEST);
		}
		if (userService.saveUser(user)){
			logger.info("leaving signup with status ok");
			return new ResponseEntity<String>("User Registered Successfully", HttpStatus.CREATED);
		}
		logger.info("leaving signup with status conflict");
		return new ResponseEntity<String>("User Already Exists", HttpStatus.CONFLICT);
	}

	@GetMapping("searchHistory/{emailId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<Search>> getSearchHistory(@PathVariable("emailId") String emailId) {
		logger.info("inside getSearchHistory");
		List<Search> searchList = null;
		if (emailId != null) {
			searchList = userService.listAllSearches(emailId);
			if (searchList != null){
				logger.info("leaving getSearchHistory with status ok");
				return new ResponseEntity<List<Search>>(searchList, HttpStatus.OK);
			}
		}
		logger.info("leaving getSearchHistory with status bad request");
		return new ResponseEntity<List<Search>>(searchList, HttpStatus.BAD_REQUEST);
	}

	@PutMapping("addSearch/{emailId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> addSearch(@PathVariable String emailId, @RequestBody Search search) {
		logger.info("inside addSearch");
		if (emailId != null && search != null && !emailId.equals("")) {
			if (userService.addSearch(emailId, search)){
				logger.info("leaving addSearch with status ok");
				return new ResponseEntity<String>("Search Added", HttpStatus.OK);
			}
		}
		logger.info("leaving addSearch with status bad request");
		return new ResponseEntity<String>("Wrong search details", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("deleteSearch/{emailId}/{searchId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> deleteSearchHistory(@PathVariable("emailId") String emailId,
			@PathVariable("searchId") long searchId) {
		logger.info("inside deleteSearchHistory");
		if (emailId != null && searchId != 0) {
			if (userService.deleteSearchHistory(emailId, searchId)){
				logger.info("leaving deleteSearchHistory with status ok");
				return new ResponseEntity<String>("Search Deleted", HttpStatus.OK);
			}
			logger.info("leaving deleteSearchHistory with status bad request");
			return new ResponseEntity<String>("User doesn't exist", HttpStatus.BAD_REQUEST);
		}
		logger.info("leaving deleteSearchHistory with status bad request");
		return new ResponseEntity<String>("Wrong Email", HttpStatus.BAD_REQUEST);
	}
}
