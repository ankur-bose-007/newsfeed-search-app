package com.mocktest.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mocktest.core.entity.User;
import com.mocktest.core.service.AdminService;

@RestController
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	
	@GetMapping("searchUser/{emaildId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<User> searchUserByName(@PathVariable String emailId){
		User user=null;
		if(emailId!=null){
			if(adminService.getUserByUsername(emailId)!=null)
				return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("searchAllUsers")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<User>> searchAllUser(){
		List<User> usersList=adminService.getAllUsers();
		if(!usersList.isEmpty())
			return new ResponseEntity<List<User>>(usersList,HttpStatus.OK);
		return new ResponseEntity<List<User>>(usersList,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("blacklist/{emailId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> blacklistUser(@PathVariable String emailId){
		
		if(adminService.blacklist(emailId))
			return new ResponseEntity<String>("User blacklisted",HttpStatus.OK);
		
		return new ResponseEntity<String>("Wrong User Details",HttpStatus.BAD_REQUEST);
	}
}
