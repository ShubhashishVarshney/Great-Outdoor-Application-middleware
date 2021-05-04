package com.cg.goa.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.goa.dao.IUserRepository;
import com.cg.goa.exception.IDNotFoundException;
import com.cg.goa.exception.UserNotFoundException;
import com.cg.goa.model.UserdataModel;
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepository userrepo;

	@Autowired
	private EMParserUser parser;
	/*
	 * A default Constructor with no implementation
	 */
	public UserServiceImpl() {

	}
	
	/*
	 * Corresponding Getters and Setters for private members
	 * 
	 */
	public UserServiceImpl(IUserRepository userrepo) {

		super();
		this.userrepo = userrepo;
		this.parser = new EMParserUser();
	}
	/*
	 * service implementation for Adding New User
	 * @Throws UserNotFoundException
	 */
	@Transactional
	@Override
	public UserdataModel addUser(UserdataModel user) throws UserNotFoundException {
		if (user == null || userrepo.findById(user.getUserId())==null) {
			throw new UserNotFoundException("enter the correct user");
		}
		 parser.parse(userrepo.save(parser.parse(user)));
		return user;
	}
	/*
	 * service implementation for Log in User
	 * @Throws UserNotFoundException
	 */
	@Transactional
	@Override
	public String loginUser(UserdataModel u) throws IDNotFoundException {
		int userId = u.getUserId();
		String userPassword = u.getUserPassword();
		 UserdataModel a = parser.parse(userrepo.findById(userId).orElse(null));
		 if((!(a.getUserId()==userId)) || !a.getUserPassword().equalsIgnoreCase(userPassword)) { 
			   throw new IDNotFoundException("Id not found");
			   }
		 return "LOGIN SUCCESSFULLY";
	}
	/*
	 * service implementation for Log Out New User
	 */
	@Transactional
	@Override
	public String logout() {
		
		return "LOGOUT SUCCESSFULLY";
	}

}
