package mockTest.unitTesting;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

import com.mocktest.core.entity.Search;
import com.mocktest.core.entity.User;
import com.mocktest.core.repo.UserRepository;
import com.mocktest.core.service.UserService;
import org.junit.Assert;

public class UserServiceTest {
	@Rule
	public MockitoRule rule=MockitoJUnit.rule();
	
	@InjectMocks
	private UserService userService;
	@Mock
	private UserRepository userRepository;
	private Optional<User> optionalUser;
	private Search search;
	private User user;
	@Mock
	PasswordEncoder passwordEncoder;
	@Before
	public void initialize(){
		user=new User();
		user.setEmailId("bose.ankur007@gmail.com");
		user.setId(1);
		user.setPassword("Password@10");
		user.setRoles(new ArrayList());
		user.setAdmin(true);
		user.setSearchList(new ArrayList());
		user.setUserName("Ankur Bose");
		optionalUser=Optional.of(user);
		search=new Search();
		search.setId(1);
		search.setKeyword("Barcelona");
		search.setSearchTime(LocalDateTime.now());
	}
	
	//tests for saveUser
	
	@Test
	public void nullUserSaveUser(){
		Assert.assertEquals(false, userService.saveUser(null));
	}
	@Test
	public void userPresent(){
		when(userRepository.findByEmailId(user.getEmailId())).thenReturn(optionalUser);
		Assert.assertEquals(false,userService.saveUser(user));
	}
	@Test
	public void userNotPresent(){
		when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
		Assert.assertEquals(true, userService.saveUser(user));
	}
	
	//tests for isActive
	@Test
	public void nullUserIsActive(){
		Assert.assertEquals(false, userService.isActive(null));
	}
	@Test
	public void notPresent(){
		when(userRepository.findByEmailId(user.getEmailId())).thenReturn(Optional.empty());
		Assert.assertEquals(false, userService.isActive(user));
	}
	@Test
	public void notActive(){
		when(userRepository.findByEmailId(user.getEmailId())).thenReturn(optionalUser);
		Assert.assertEquals(false, userService.isActive(user));
	}
	@Test
	public void isActive(){
		user.setActive(true);
		when(userRepository.findByEmailId(user.getEmailId())).thenReturn(optionalUser);
		Assert.assertEquals(true, userService.isActive(user));
	}
	
	//test for listAllSearches
	@Test
	public void emailNull(){
		Assert.assertEquals(null, userService.listAllSearches(null));
	}
	@Test
	public void emailEmpty(){
		Assert.assertEquals(null, userService.listAllSearches(""));
	}
	@Test
	public void userNotFound(){
		when(userRepository.findByEmailId(user.getEmailId())).thenReturn(Optional.empty());
		Assert.assertEquals(null, userService.listAllSearches(user.getEmailId()));
	}
	@Test
	public void userFound(){
		when(userRepository.findByEmailId(user.getEmailId())).thenReturn(optionalUser);
		Assert.assertEquals(new ArrayList<User>(), userService.listAllSearches(user.getEmailId()));
	}
	
	//test for addSearch
	@Test
	public void searchObjNull(){
		Assert.assertEquals(false,userService.addSearch(user.getEmailId(),null));
	}
	@Test
	public void emailIdNull(){
		Assert.assertEquals(false,userService.addSearch(null,search));
	}
	@Test
	public void emailIdEmpty(){
		Assert.assertEquals(false,userService.addSearch("",search));
	}
	@Test
	public void emailNotPresent(){
		when(userRepository.findByEmailId(user.getEmailId())).thenReturn(Optional.empty());
		Assert.assertEquals(false,userService.addSearch(user.getEmailId(),search));
	}
	@Test
	public void emailPresent(){
		when(userRepository.findByEmailId(user.getEmailId())).thenReturn(optionalUser);
		Assert.assertEquals(true,userService.addSearch(user.getEmailId(),search));
	}
	
	//deleteSearchHistory
	@Test
	public void searchEmailIdNull(){
		Assert.assertEquals(false,userService.deleteSearchHistory(null, search.getId()));
	}
	@Test
	public void searchEmailIdEmpty(){
		Assert.assertEquals(false,userService.deleteSearchHistory("", search.getId()));
	}
	@Test
	public void searchIdZero(){
		Assert.assertEquals(false,userService.deleteSearchHistory(user.getEmailId(), 0));
	}
	@Test
	public void searchUserNotFound(){
		when(userRepository.findByEmailId(user.getEmailId())).thenReturn(Optional.empty());
		Assert.assertEquals(false,userService.deleteSearchHistory(user.getEmailId(), search.getId()));
	}
	@Test
	public void searchDeleted(){
		when(userRepository.findByEmailId(user.getEmailId())).thenReturn(optionalUser);
		Assert.assertEquals(true,userService.deleteSearchHistory(user.getEmailId(), search.getId()));
	}
}
