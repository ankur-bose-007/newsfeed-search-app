package com.mocktest.core.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.mocktest.core.entity.Search;
import com.mocktest.core.entity.User;
import com.mocktest.core.security.JwtProvider;
import com.mocktest.core.service.UserService;

@RestController
@RequestMapping("user")
public class UserController extends GlobalErrorHandlerController{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	@Autowired
	private JwtProvider jwtProvider;
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("getUserDetails/{emailId}")
	public ResponseEntity<User> getUserDetails(@PathVariable("emailId") String emailId) {
		User user=null;
		if(emailId!=null&&!emailId.equals("")){
			user=userService.getUserDetails(emailId);
			if(user!=null)
				return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("login")
	public ResponseEntity<String> login(@Valid @RequestBody User user){
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				user.getEmailId(),
				user.getPassword()
				)
			);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		if(!userService.isActive(user))
			return new ResponseEntity<String>("User doesn't exists",HttpStatus.BAD_REQUEST);
        String jwt = jwtProvider.generateJwtToken(authentication);
        return new ResponseEntity<String>(jwt,HttpStatus.OK);
	}
	
	@PostMapping("signup")
	public ResponseEntity<String> signup(@Valid @RequestBody User user){
		if(user==null)
			return new ResponseEntity<String>("User null",HttpStatus.BAD_REQUEST);
		if(userService.saveUser(user))
			return new ResponseEntity<String>("User Registered Successfully",HttpStatus.CREATED);
		return new ResponseEntity<String>("User Already Exists",HttpStatus.CONFLICT);
	}
	
	@GetMapping("searchHistory/{emailId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<Search>> getSearchHistory(@PathVariable("emailId") String emailId){
		List<Search> searchList=null;
		if(emailId!=null){
			searchList=userService.listAllSearches(emailId);
			if(searchList!=null)
				return new ResponseEntity<List<Search>>(searchList,HttpStatus.OK);
		}
		return new ResponseEntity<List<Search>>(searchList,HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("addSearch/{emailId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> addSearch(@PathVariable String emailId,@RequestBody Search search){
		if(emailId!=null&&search!=null&&!emailId.equals("")){
			if(userService.addSearch(emailId, search))
				return new ResponseEntity<String>("Search Added",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Wrong search details",HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("deleteSearch/{emailId}/{searchId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> deleteSearchHistory(@PathVariable("emailId") String emailId,@PathVariable("searchId") long searchId){
		if(emailId!=null&&searchId!=0){
			if(userService.deleteSearchHistory(emailId,searchId))
				return new ResponseEntity<String>("Search Deleted",HttpStatus.OK);
			return new ResponseEntity<String>("User doesn't exist",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Wrong Email",HttpStatus.BAD_REQUEST);
	}
}
