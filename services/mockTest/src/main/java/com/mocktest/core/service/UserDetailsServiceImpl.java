package com.mocktest.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mocktest.core.entity.Role;
import com.mocktest.core.entity.User;
import com.mocktest.core.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String userEmailId) throws UsernameNotFoundException {
		
		User user=userRepository.findByEmailId(userEmailId).orElseThrow(()->new UsernameNotFoundException("Username not found with username or email"+userEmailId));
		
		List<Role> roles=user.getRoles();
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		roles.forEach(role->{
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getEmailId(),user.getPassword(),authorities);
	}
}
