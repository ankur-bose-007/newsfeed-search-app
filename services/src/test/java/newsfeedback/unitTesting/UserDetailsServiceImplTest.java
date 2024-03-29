package newsfeedback.unitTesting;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.newsfeedback.core.entity.Role;
import com.newsfeedback.core.entity.Search;
import com.newsfeedback.core.entity.User;
import com.newsfeedback.core.repo.UserRepository;
import com.newsfeedback.core.service.UserDetailsServiceImpl;

/**
 * @author 729706
 * Name: Ankur Bose
 * Date: Feb 28, 2019
 */
public class UserDetailsServiceImplTest {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@InjectMocks
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Mock
	private UserRepository userRepository;
	Optional<User> optionalUser;
	User user;
	UserDetails userDetails;

	@Before
	public void initialize() {
		user = new User();
		user.setEmailId("bose.ankur007@gmail.com");
		user.setId(1);
		user.setActive(true);
		user.setAdmin(true);
		user.setPassword("Password@10");
		user.setRoles(new ArrayList<Role>());
		user.setSearchList(new ArrayList<Search>());
		List<Role> roles = user.getRoles();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		roles.forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		});
		userDetails = new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(),
				authorities);

		optionalUser = Optional.of(user);
	}

	@Test(expected = UsernameNotFoundException.class)
	public void usernameNotFound() {
		when(userRepository.findByEmailId("bose.ankur007@gmail.com")).thenThrow(UsernameNotFoundException.class);
		Assert.assertEquals(UsernameNotFoundException.class,
				userDetailsServiceImpl.loadUserByUsername("bose.ankur007@gmail.com"));
	}

	@Test
	public void userFound() {
		when(userRepository.findByEmailId("bose.ankur007@gmail.com")).thenReturn(optionalUser);
		Assert.assertEquals(userDetails, userDetailsServiceImpl.loadUserByUsername("bose.ankur007@gmail.com"));
	}
}
