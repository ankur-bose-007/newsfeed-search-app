package mockTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.mocktest.core.entity.User;
import com.mocktest.core.repo.UserRepository;
import com.mocktest.core.service.AdminService;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Assert;

public class AdminServiceTestClass {
	@Rule
	public MockitoRule rule=MockitoJUnit.rule();
	@InjectMocks
	private AdminService adminService;
	
	
	User user;
	Optional<User> userOptional;
	
	
	@Mock
	private UserRepository userRepository;
	@Before
	public void initialize() {
		user=new User();
		user.setEmailId("bose.ankur007@gmail.com");
		user.setId(1);
		user.setPassword("Password@10");
		user.setRoles(new ArrayList());
		user.setAdmin(true);
		user.setSearchList(new ArrayList());
		user.setUserName("Ankur Bose");
		userOptional=Optional.empty();
	}
	//tests for getUserByUsername
	@Test
	public void successfulGetUserByUsername() {
		when(userRepository.findByEmailIdContainingIgnoreCaseAndAdmin(user.getEmailId(), false)).thenReturn(new ArrayList<User>());
		Assert.assertEquals(new ArrayList<User>(), adminService.getUserByUsername(user.getEmailId()));
	}
	@Test
	public void nullGetUserByUsername(){
		Assert.assertEquals(null,adminService.getUserByUsername(null));
	}
	@Test
	public void emptyGetUserByUsername(){
		Assert.assertEquals(null,adminService.getUserByUsername(""));
	}
	@Test
	public void unsuccessfulGetUserByUsername(){
		when(userRepository.findByEmailIdContainingIgnoreCaseAndAdmin("Ankur", false)).thenReturn(null);
		Assert.assertEquals(null, adminService.getUserByUsername("Ankur"));
	}
	
	//tests for getAllUsers
	
	@Test
	public void unsuccessfulGetAllUsers(){
		when(userRepository.findAllUsers()).thenReturn(null);
		Assert.assertEquals(null, adminService.getAllUsers());
	}
	@Test
	public void successfulGetAllUsers(){
		when(userRepository.findAllUsers()).thenReturn(new ArrayList<User>());
		Assert.assertEquals(new ArrayList<User>(), adminService.getAllUsers());
	}
	
	//test for blacklist
	
	@Test
	public void successfulBlacklist(){
		when(userRepository.save(user)).thenReturn(user);
		when(userRepository.findByEmailId(user.getEmailId())).thenReturn(Optional.of(user));
		Assert.assertEquals(true, adminService.blacklist(user.getEmailId()));
	}
	@Test
	public void unsuccessfulBlacklist(){
		when(userRepository.findByEmailId(user.getEmailId())).thenReturn(userOptional);
		Assert.assertEquals(false, adminService.blacklist(user.getEmailId()));
	}
	@Test
	public void emailEmptyBlacklist(){
		Assert.assertEquals(false, adminService.blacklist(""));
	}
	@Test
	public void emailNullBlacklist(){
		Assert.assertEquals(false, adminService.blacklist(null));
	}
}