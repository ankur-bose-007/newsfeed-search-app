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
import java.util.List;

import org.junit.Assert;

public class AdminServiceTestClass {
	@Rule
	public MockitoRule rule=MockitoJUnit.rule();
	@InjectMocks
	private AdminService adminService;
	
	
	User user;
	
	
	
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
	}
	
	@Test
	public void successfulUsernameTest() {
		when(userRepository.findByEmailIdContainingIgnoreCaseAndAdmin(user.getEmailId(), false)).thenReturn(new ArrayList<User>());
		Assert.assertEquals(new ArrayList<User>(), adminService.getUserByUsername(user.getEmailId()));
	}
	
}