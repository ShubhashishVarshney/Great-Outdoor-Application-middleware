package com.cg.goa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.goa.exception.IDNotFoundException;
import com.cg.goa.exception.UserNotFoundException;
import com.cg.goa.model.UserdataModel;
import com.cg.goa.service.IUserService;

@RestController
@RequestMapping(path="/Userdata")
public class UserApi {
	
	@Autowired
	private IUserService userservice;
	
	/*
	 * to add new User
	 * return : User
	 * params : NIL
	 */
	@PostMapping
	public ResponseEntity<UserdataModel> addUser(@RequestBody UserdataModel user) throws UserNotFoundException {
		return ResponseEntity.ok(userservice.addUser(user));
	}
	/*
	 * to login User
	 * return : String
	 * params : userId , Password
	 */
	@GetMapping("login/{uid},{password}")
	public ResponseEntity<String> loginUser(@PathVariable("uid") Integer s, @PathVariable("password") String k) throws IDNotFoundException {
		UserdataModel e = new UserdataModel();
		   e.setUserId(s);
		   e.setUserPassword(k);
		return ResponseEntity.ok(userservice.loginUser(e));
	}
	
	/*
	 * to logout user
	 * return : String
	 * params : NIL
	 */
	@GetMapping("/logout")
	   public ResponseEntity<String> logoutUser() {
		   return ResponseEntity.ok(userservice.logout());
	   }
	
	

}
