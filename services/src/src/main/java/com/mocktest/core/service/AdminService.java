package com.mocktest.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mocktest.core.entity.User;
import com.mocktest.core.repo.UserRepository;

@Service
public class AdminService {
	@Autowired
	private UserRepository userRepository;
	
	public User getUserByUsername(String emailId){
		User user=null;
		if(emailId!=null&&!emailId.equals(""))
			user=userRepository.findByEmailId(emailId).orElse(null);
		return user;
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAllUsers();
	}
	
	public boolean blacklist(String emailId){
		User user=null;
		if(emailId!=null&&!emailId.equals("")){
			user=userRepository.findByEmailId(emailId).orElse(null);
			if(user!=null){
				user.setActive(false);
				return true;
			}
		}
		return false;
	}
}
