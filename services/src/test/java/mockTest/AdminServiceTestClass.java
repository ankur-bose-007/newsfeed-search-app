package mockTest;

import org.junit.Rule;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.mocktest.core.service.AdminService;

public class AdminServiceTestClass {
	@Rule
	public MockitoRule rule=MockitoJUnit.rule();
	@InjectMocks
	private AdminService adminService;
	
	
}