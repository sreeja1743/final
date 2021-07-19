package com.app.dca.service;

import java.util.List;


import com.app.dca.entity.Users;
import com.app.dca.exception.UnknownUserException;
import com.app.dca.exception.ValidateUserException;


public interface IUserService {

	public Users addNewUser(Users user) throws ValidateUserException;
	public boolean validateUser(Users user) throws ValidateUserException;
	public String signin(String UserName,String Password) throws UnknownUserException;
	public String signOut(Users user);
	public boolean forgotPassword(int id, String oldPassword, String newPassword) throws  UnknownUserException;
	public int getDeveloperByUserId(int userId);
}
