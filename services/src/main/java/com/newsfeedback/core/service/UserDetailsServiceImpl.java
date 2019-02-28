package com.newsfeedback.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.newsfeedback.core.entity.Role;
import com.newsfeedback.core.entity.User;
import com.newsfeedback.core.repo.UserRepository;

/**
 * @author 729706
 * Name: Ankur Bose
 * Date: Feb 28, 2019
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	private static final Logger logger=LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Transactional
	public UserDetails loadUserByUsername(String userEmailId) throws UsernameNotFoundException {
		logger.info("inside loadUserByUsername");
		User user = userRepository.findByEmailId(userEmailId).orElseThrow(
				() -> new UsernameNotFoundException("Username not found with username or email" + userEmailId));

		List<Role> roles = user.getRoles();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		roles.forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		});
		logger.info("leaving loadUserByUsername");
		return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(),
				authorities);
	}
}
