package com.app.dca.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dca.entity.Developer;
import com.app.dca.entity.Users;
import com.app.dca.exception.UnknownUserException;
import com.app.dca.exception.ValidateUserException;
import com.app.dca.repository.UserRepository;
import com.app.dca.util.DeveloperConstants;
import com.app.dca.service.*;

@Service
public class IUserServiceImpl implements IUserService{
	
	@Autowired
	private UserRepository repo;
   @Autowired
   private IDeveloperService devSer;

	public Users addNewUser(Users user) throws ValidateUserException {
		validateUser(user);
		return repo.save(user);
	}

	public boolean validateUser(Users user) throws ValidateUserException {
		if (!user.getUserName().matches("[A-Za-z0-9]+")) {
			throw new ValidateUserException(DeveloperConstants.USERNAME_CANNOT_BE_EMPTY);
		}
		if (!user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
			throw new ValidateUserException(DeveloperConstants.PASSWORD_CANNOT_BE_EMPTY);
		}
		return true;
	}

	public String signin(String UserName,String Password) throws UnknownUserException{
		try {
			if (repo.existuser(UserName,Password)>0) {
				System.out.println(1);
	//		
				return "successfully signed in";
			}
			System.out.println(2);
			return "Invalid";
		}catch(Exception e) {
			throw new  UnknownUserException("User is invalid") ;
		}
	}

	public String signOut(Users user) {
		Optional<Users> u = repo.findById(user.getUserId());
		if (u.isPresent()) {
			Users obj = u.get();
			if (obj.isLoggedIn() == true) {
				obj.setLoggedIn(false);
				repo.save(obj);
				return "successfully signed out";
			} else {
				return "failed to sign out";
			}
		}
		return "failed to sign out";

	}

	public boolean forgotPassword(int id, String oldPassword, String newPassword) throws  UnknownUserException {
		try {
			if (repo.existsById(id)) {
				if (oldPassword.equals(newPassword)) {
					return false;
				} else {
					if (newPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
						throw new ValidateUserException(DeveloperConstants.PASSWORD_CANNOT_BE_EMPTY);
					}
					repo.replace(id, oldPassword, newPassword);
					return true;
				}
			} else {
				throw new  UnknownUserException();
			}
		} catch (Exception e) {
			throw new  UnknownUserException("User not Exists");
		}
	}
	public int getUserId(String username) {
		return repo.getUserId(username);
	}

	@Override
	public int getDeveloperByUserId(int userId) {
	   List<Developer> dev = devSer.getAllDevelopers();
	   for (Developer developer : dev) {
		 if(developer.getUser().getUserId() == userId) {
			 return developer.getDevId();
		 }
	}
		return 0;
	}

}
