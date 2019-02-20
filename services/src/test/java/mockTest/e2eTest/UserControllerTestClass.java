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
import static org.hamcrest.core.StringContains.containsString;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mocktest.core.Application;
import com.mocktest.core.entity.Search;
import com.mocktest.core.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class UserControllerTestClass {
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
	
	//test for login
	@Test
	public void successfulLogin() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	public void nullEmailLogin() throws Exception{
		user1.setPassword(null);
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void nullPasswordLogin() throws Exception{
		user1.setEmailId(null);
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void emptyEmailLogin() throws Exception{
		user1.setEmailId("");
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void emptyPasswordLogin() throws Exception{
		user1.setPassword("");
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void emailPatternWrongLogin() throws Exception{
		user1.setEmailId("bose.ankur");
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void passwordPatternWrongLogin() throws Exception{
		user1.setPassword("password");;
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void unsuccessfulLogin() throws Exception{
		user1.setEmailId("bose.ankur001@gmail.com");
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void userBlocked() throws Exception{
		user1.setEmailId("sourav.ghosh1@mail.com");
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().string(containsString("User doesn't exist")));
	}
	
	//test for signup
	@Test
	public void successfulSignup() throws Exception{
		user1=new User();
		user1.setEmailId("user.123@gmail.com");
		user1.setPassword("Password@10");
		mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string(containsString("User Registered Successfully")));
	}
	@Test
	public void nullEmailSignup() throws Exception{
		user1.setEmailId(null);
		mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void nullPasswordSignup() throws Exception{
		user1.setPassword(null);
		mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void emptyEmailSignup() throws Exception{
		user1.setEmailId("");
		mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void emptyPasswordSignup() throws Exception{
		user1.setPassword("");
		mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void nullUserSignup() throws Exception{
		user1=null;
		mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadGateway());
	}
	@Test
	public void existingUserSignup() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isConflict())
				.andExpect(MockMvcResultMatchers.content().string(containsString("User Already Exists")));
	}
	
	//test for getUserDetails
	@Test
	public void nullEmailIdGetUserDetails() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserDetails"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	@Test
	@WithMockUser(roles="USER")
	public void emptyEmailIdGetUserDetails() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserDetails/''"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	@WithMockUser(roles="USER")
	public void successfulGetUserDetails() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserDetails/bose.ankur007@gmail.com")
				.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqZWV0LmFua3VyMDA3QGdtYWlsLmNvbSIsImlhdCI6MTU1MDY0Njk2MCwiZXhwIjoxNTUxNTEwOTYwfQ.G2IBN8G1U8UoRHxVeHQOJu9JJEW07oNDWiLJxYP5gEvwGrxtMsapwU6GuU5yXnvq3SZqi8P9giAktxDkU5wvfg"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	//test for searchHistory
	@Test
	@WithMockUser(roles="USER")
	public void successfulGetSearchHistory() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/user/searchHistory/jeet.ankur007@gmail.com"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	public void nullEmailIdGetSearchHistory() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/user/searchHistory")
				.content(mapper.writeValueAsString(user1))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	//test for add search
	@Test
	@WithMockUser(roles="USER")
	public void successfulAddSearch() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.put("/user/addSearch/bose.ankur007@gmail.com")
				.content(mapper.writeValueAsString(search))
				.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqZWV0LmFua3VyMDA3QGdtYWlsLmNvbSIsImlhdCI6MTU1MDY0Njk2MCwiZXhwIjoxNTUxNTEwOTYwfQ.G2IBN8G1U8UoRHxVeHQOJu9JJEW07oNDWiLJxYP5gEvwGrxtMsapwU6GuU5yXnvq3SZqi8P9giAktxDkU5wvfg")
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Search Added")));
	}
	@Test
	public void emptyEmailAddSearch() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.put("/user/addSearch/''")
				.content(mapper.writeValueAsString(search))
				.contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isBadGateway());	
	}
	//test for deleteSearch
	@Test
	@WithMockUser(roles="USER")
	public void successfulDeleteSearch() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/deleteSearch/bose.ankur007@gmail.com/27"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	@WithMockUser(roles="USER")
	public void unsuccessfulDeleteSearch() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/deleteSearch/bose.ankur007@gmail.c/27"))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
