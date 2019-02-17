package com.mocktest.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mocktest.core.entity.Role;
import com.mocktest.core.entity.Search;
import com.mocktest.core.entity.User;
import com.mocktest.core.repo.RoleRepository;
import com.mocktest.core.repo.UserRepository;
@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder; 
	Search sr;
	
	public boolean saveUser(User user){
		if(user!=null&&!userRepository.findByEmailId(user.getEmailId()).isPresent()){
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setActive(true);
			user.setAdmin(false);
			userRepository.save(user);
			Role role=new Role();
			role.setRoleName("ROLE_USER");
			ArrayList<Role> rolesList=new ArrayList<Role>();
			rolesList.add(role);
			user.setRoles(rolesList);
			return true;
		}
		return false;
	}
	
	public boolean isActive(User user){
		if(user!=null&&userRepository.findByEmailId(user.getEmailId()).isPresent()){
			user=userRepository.findByEmailId(user.getEmailId()).orElse(null);
			if(user!=null&&user.isActive())
				return true;
		}
		return false;
	}
	
	public List<Search> listAllSearches(String emailId){
		List<Search> searchList=null;
		User user=null;
		if(emailId!=null&&!emailId.equals("")){
			user=userRepository.findByEmailId(emailId).orElse(null);
			if(user!=null)
				searchList=user.getSearchList();
		}
		return searchList;
	}
	
	public User getUserDetails(String emailId) {
		User user=null;
		if(emailId!=null&&!emailId.equals(""))
			user=userRepository.findByEmailId(emailId).orElse(null);
		return user;
	}
	
	public boolean addSearch(String emailId,Search search){
		List<Search> searchList=null;
		User user=null;
		if(userRepository.findByEmailId(emailId).isPresent()&&search!=null){
			user=userRepository.findByEmailId(emailId).orElse(null);
			if(user!=null){
				searchList=user.getSearchList();
				searchList.add(search);
				user.setSearchList(searchList);
				userRepository.save(user);
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteSearchHistory(String emailId,long searchId){
		
		if(emailId!=null&&!emailId.equals("")&&searchId!=0){
			User user = userRepository.findByEmailId(emailId).get();
			if(user!=null){
				user.getSearchList().forEach(searchPojo->{
					if(searchPojo.getId()==searchId)
						sr = searchPojo;
				});
			user.getSearchList().remove(sr);
		}
		return true;
	}
		return false;
	}
}
