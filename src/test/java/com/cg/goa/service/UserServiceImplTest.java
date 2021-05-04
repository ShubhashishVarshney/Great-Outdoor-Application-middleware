package com.cg.goa.service;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.goa.dao.IUserRepository;
import com.cg.goa.entity.Userdata;
import com.cg.goa.model.UserdataModel;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	@Mock
	private IUserRepository userrepo;

	@InjectMocks /*
					 * injecting User repository marked as @Mock into user service
					 * implementation
					 */
	private UserServiceImpl usImpl;
	/*
	 * Test Case 1 - to Add  User
	 */
	@Test
	@DisplayName("UserServiceImpl::Add User should add new user to User Model List")
	void testAddUser() {
		Userdata testdata=new Userdata("shubh",1,"admin","abc");
		UserdataModel expected=new UserdataModel("shubh",1,"admin","abc");
		Mockito.when(userrepo.findById(testdata.getUserId())).thenReturn(Optional.of(testdata));
		UserdataModel actual = usImpl.addUser(expected);

		assertEquals(expected, actual);
	}
	/*
	 * Test Case 2 - to Login User
	 * 
	 */
	@Test
	@DisplayName("UserServiceImpl::login user should be able to view product List")
	void testLoginUser() {
		Userdata testdata=new Userdata("shubh",1,"admin","admin");
		UserdataModel expected=new UserdataModel("shubh",1,"admin","admin");
		Mockito.when(userrepo.findById(testdata.getUserId())).thenReturn(Optional.of(testdata));
		String actual =usImpl.loginUser(expected);
		assertEquals("LOGIN SUCCESSFULLY", actual);
	}
	/*
	 * Test Case 3 - to Logout
	 * 
	 */
	@Test
	@DisplayName("UserServiceImpl::logout user should be not able to do anything")
	void testLogout() {
		String actual =  usImpl.logout();
		assertEquals("LOGOUT SUCCESSFULLY", actual);
	}
}
