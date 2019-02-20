package mockTest.e2eTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mocktest.core.Application;
import com.mocktest.core.entity.Search;
import com.mocktest.core.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class AdminControllerTestClass {
	@Autowired
	private WebApplicationContext webApplicationContext;
	ObjectMapper mapper;
	
	private MockMvc mockMvc;
	User user1;
	Search search;
	
	@Before
	public void initialize(){
		mapper=new ObjectMapper();
		mockMvc=MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		user1=new User();
		user1.setEmailId("bose.ankur007@gmail.com");
		user1.setPassword("Password@10");
		search=new Search();
		search.setKeyword("test search");
	}
	
	//test for searchUser
	@Test
	@WithMockUser(roles="ADMIN")
	public void successfulSearchUser() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/searchUser/'a'"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	@WithMockUser(roles="ADMIN")
	public void unsuccessfulSearchUser() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/searchUser/"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	//test for searchAllUsers
	@Test
	@WithMockUser(roles="ADMIN")
	public void searchUser() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/searchAllUsers"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	//test for blacklist
	@Test
	@WithMockUser(roles="ADMIN")
	public void successfulBlacklist() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/blacklist/sourav.ghosh10@mail.com"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	@WithMockUser(roles="ADMIN")
	public void unSuccessfulBlacklist() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/blacklist/sourav.ghosh11@mail.com"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
