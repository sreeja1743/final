package com.app.dca.restcontroller;


import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dca.entity.SuccessMsg;
import com.app.dca.entity.Users;
import com.app.dca.exception.UnknownUserException;
import com.app.dca.exception.ValidateUserException;
import com.app.dca.service.IUserService;
import com.app.dca.service.IUserServiceImpl;
import com.app.dca.util.DeveloperConstants;

import io.swagger.annotations.Api;
@Validated
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
@Api(value = "Developer application")
public class UserRestController {
	
	
	@Autowired
	private IUserServiceImpl service;

	@GetMapping("/signin/{UserName}/{Password}")
	public SuccessMsg usersignin(@PathVariable("UserName")String UserName,@PathVariable("Password")String Password) throws UnknownUserException {
		service.signin(UserName,Password);
		return new SuccessMsg(DeveloperConstants.USER_LOGGEDIN);
	}

	@PostMapping("/signout")
	public String signout(@RequestBody Users user) {
		return service.signOut(user);
	}

	@PostMapping("/User")
	public SuccessMsg SaveUser(@RequestBody Users user) throws ValidateUserException {
		service.addNewUser(user);
		return new SuccessMsg(DeveloperConstants.USER_ADDED +" "+ user.getUserId());
	}

	@PostMapping("/forgot/{id}/{old}/{new1}")
	public boolean forgotPassword(@PathVariable("id") int id, @PathVariable("old") String old,
			@PathVariable("new1") String new1) throws UnknownUserException {
		return service.forgotPassword(id, old, new1);
	}
	@GetMapping("/username/{username}")
	public int getUserId(@PathVariable("username") String username) {
		return service.getUserId(username);
	}
    @GetMapping("/userId/{userId}")
    public int getDevId(@PathVariable int userId) {
    	return service.getDeveloperByUserId(userId);
    }
}