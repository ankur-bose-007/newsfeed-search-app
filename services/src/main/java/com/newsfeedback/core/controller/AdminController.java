package com.newsfeedback.core.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newsfeedback.core.entity.User;
import com.newsfeedback.core.service.AdminService;

/**
 * @author 729706
 * Name: Ankur Bose
 * Date: Feb 28, 2019
 */
@RestController
@RequestMapping("admin")
public class AdminController extends GlobalErrorHandlerController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private AdminService adminService;

	@GetMapping("searchUser/{emailId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<User>> searchUserByName(@PathVariable("emailId") String emailId) {
		logger.info("inside searchUserByName");
		List<User> usersList = null;
		if (emailId != null) {
			if (adminService.getUserByUsername(emailId) != null) {
				usersList = adminService.getUserByUsername(emailId);
				logger.info("leaving searchUserByName with status ok");
				return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
			}
		}
		logger.info("leaving searchUserByName with status bad request");
		return new ResponseEntity<List<User>>(usersList, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("searchAllUsers")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<User>> searchAllUser() {
		logger.info("inside searchAllUser");
		List<User> usersList = adminService.getAllUsers();
		if (!usersList.isEmpty()){
			logger.info("leaving searchAllUser with status ok");
			return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
		}
		logger.info("leaving searchAllUser with status bad request");
		return new ResponseEntity<List<User>>(usersList, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("blacklist/{emailId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> blacklistUser(@PathVariable("emailId") String emailId) {
		logger.info("inside blacklistUser");
		if (adminService.blacklist(emailId)){
			logger.info("leaving blacklistUser with status ok");
			return new ResponseEntity<String>("User blacklisted", HttpStatus.OK);
		}
		logger.info("leaving blacklistUser with status bad request");
		return new ResponseEntity<String>("Wrong User Details", HttpStatus.BAD_REQUEST);
	}
}
