package com.newsfeedback.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.newsfeedback.core.entity.Role;
import com.newsfeedback.core.entity.Search;
import com.newsfeedback.core.entity.User;
import com.newsfeedback.core.repo.UserRepository;

/**
 * @author 729706
 * Name: Ankur Bose
 * Date: Feb 28, 2019
 */
@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private static final Logger logger=LoggerFactory.getLogger(UserService.class);
	Search sr;

	public boolean saveUser(User user) {
		logger.info("inside saveUser");
		if (user != null && !userRepository.findByEmailId(user.getEmailId()).isPresent()) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setActive(true);
			user.setAdmin(false);
			userRepository.save(user);
			Role role = new Role();
			role.setRoleName("ROLE_USER");
			ArrayList<Role> rolesList = new ArrayList<Role>();
			rolesList.add(role);
			user.setRoles(rolesList);
			logger.info("leaving saveUser with true");
			return true;
		}
		logger.info("leaving saveUser with false");
		return false;
	}

	public boolean isActive(User user) {
		logger.info("inside isActive");
		if (user != null && userRepository.findByEmailId(user.getEmailId()).isPresent()) {
			user = userRepository.findByEmailId(user.getEmailId()).orElse(null);
			if (user != null && user.isActive()){
				logger.info("leaving isActive with true");
				return true;
			}
		}
		logger.info("leaving isActive with false");
		return false;
	}

	public List<Search> listAllSearches(String emailId) {
		logger.info("inside listAllSearches");
		List<Search> searchList = null;
		User user = null;
		if (emailId != null && !emailId.equals("")) {
			user = userRepository.findByEmailId(emailId).orElse(null);
			if (user != null)
				searchList = user.getSearchList();
		}
		logger.info("leaving listAllSearches");
		return searchList;
	}

	public User getUserDetails(String emailId) {
		logger.info("inside getUserDetails");
		User user = null;
		if (emailId != null && !emailId.equals(""))
			user = userRepository.findByEmailId(emailId).orElse(null);
		logger.info("leaving getUserDetails");
		return user;
	}

	public boolean addSearch(String emailId, Search search) {
		List<Search> searchList = null;
		User user = null;
		logger.info("inside addSearch");
		if (userRepository.findByEmailId(emailId).isPresent() && search != null && emailId != null
				&& !emailId.equals("")) {
			user = userRepository.findByEmailId(emailId).orElse(null);
			if (user != null) {
				searchList = user.getSearchList();
				searchList.add(search);
				user.setSearchList(searchList);
				userRepository.save(user);
				logger.info("leaving addSearch with true");
				return true;
			}
		}
		logger.info("leaving addSearch with false");
		return false;
	}

	public boolean deleteSearchHistory(String emailId, long searchId) {
		logger.info("inside deleteSearchHistory");
		if (emailId != null && !emailId.equals("") && searchId != 0) {
			User user = userRepository.findByEmailId(emailId).orElse(null);
			if (user != null) {
				user.getSearchList().forEach(searchPojo -> {
					if (searchPojo.getId() == searchId)
						sr = searchPojo;
				});
				user.getSearchList().remove(sr);
				logger.info("leaving deleteSearchHistory with true");
				return true;
			}

		}
		logger.info("leaving deleteSearchHistory with false");
		return false;
	}
}
