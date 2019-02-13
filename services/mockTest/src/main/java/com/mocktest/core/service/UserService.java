package com.mocktest.core.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mocktest.core.entity.Role;
import com.mocktest.core.entity.User;
import com.mocktest.core.repo.UserRepository;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder; 
	public boolean saveUser(User user){
		if(user!=null&&!userRepository.existsById(user.getId())){
			Role role=new Role();
			role.setRoleName("ROLE_USER");
			ArrayList<Role> rolesList=new ArrayList<Role>();
			rolesList.add(role);
			user.setRoles(rolesList);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return true;
		}
		return false;
	}
	
}
