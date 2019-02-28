package com.newsfeedback.core.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newsfeedback.core.entity.User;
import com.newsfeedback.core.repo.UserRepository;

/**
 * @author 729706
 * Name: Ankur Bose
 * Date: Feb 28, 2019
 */
@Service
public class AdminService {
	@Autowired
	private UserRepository userRepository;
	private static final Logger logger=LoggerFactory.getLogger(AdminService.class);
	public List<User> getUserByUsername(String emailId) {
		logger.info("inside getUserByUsername");
		List<User> user = null;
		if (emailId != null && !emailId.equals(""))
			user = userRepository.findByEmailIdContainingIgnoreCaseAndAdmin(emailId, false);
		logger.info("leaving getUserByUsername");
		return user;
	}

	public List<User> getAllUsers() {
		logger.info("inside getAllUsers");
		return userRepository.findAllUsers();
	}

	public boolean blacklist(String emailId) {
		logger.info("inside blacklist");
		User user = null;
		if (emailId != null && !emailId.equals("")) {
			user = userRepository.findByEmailId(emailId).orElse(null);
			if (user != null) {
				user.setActive(false);
				userRepository.save(user);
				logger.info("leaving blacklist");
				return true;
			}
		}
		logger.info("leaving blacklist");
		return false;
	}
}
