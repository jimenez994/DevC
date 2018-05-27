package com.zeus.DevC.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zeus.DevC.Models.User;
import com.zeus.DevC.Repositories.PortfolioRepository;
import com.zeus.DevC.Repositories.UserRepository;

@Service
public class UserService {
	
	String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	Pattern pattern = Pattern.compile(regex);
	
	private BCryptPasswordEncoder bcrypt; 
	private UserRepository userRepo;
	private PortfolioRepository _Pr;
	
	public UserService(UserRepository userRepo, PortfolioRepository _Pr){
		this.userRepo = userRepo;
		this._Pr = _Pr;
		this.bcrypt = new BCryptPasswordEncoder();
	}
	
//	User Crud
	public Map<String, String> create(User user){
//		Dictionary of messages
		Map<String,String> msg = new HashMap<String, String>();
		
		User eUser  = userRepo.findByEmail(user.getEmail());
		if (eUser == null ) {
			Matcher matcher = pattern.matcher(user.getEmail());
//			Checking Email
			if(!matcher.matches()) {
				msg.put("email", "Email is invalid");
			}
//			Checking name
			if(user.getName().length() < 1) {
				msg.put("name", "Name field is required");
			}
//			Checking password
			if(user.getPassword().length() < 5) {
				msg.put("password", "Password must be at least 6 characters");
			}
//			If credentials is valid -> register user
			if(msg.isEmpty()) {
				user.setPassword(bcrypt.encode( user.getPassword()));
				userRepo.save(user);
				msg.put("user_id", user.getId()+"");
				msg.put("name", user.getName());
				msg.put("email", user.getEmail());
				msg.put("avatar", "something");
				msg.put("success", "You successfully created a User");
			}
			return msg;
		}
		msg.put("email", "Email is already taken");
		return msg;
	}
	
	
	public Map<String, String> login(String email,String password){
		Map<String,String> msg = new HashMap<String, String>();
		User eUser = userRepo.findByEmail(email);
		Matcher matcher = pattern.matcher(email);
//		Checking Password
		if(password.isEmpty()) {
			msg.put("password", "Password is required");
		}
//		Checking Email
		if(!matcher.matches()) {
			msg.put("email", "Email is invalid");
		}else if(eUser == null ) {
			msg.put("email", "Email is not in database");
		}else {
			if(bcrypt.matches(password, eUser.getPassword())) {
				msg.put("success", "success");
				msg.put("user_id", eUser.getId()+"");
				msg.put("name", eUser.getName());
				msg.put("email", eUser.getEmail());
				msg.put("avatar", "something");				
				return msg;
			}else {
				msg.put("password", "Invalid password");
				return msg;
			}
		}
		return msg;
	}
	
	public Map<String, String> update(User user){
		userRepo.save(user);
		Map<String,String> msg = new HashMap<String, String>();
		msg.put("message", "You successfully updated");
		return msg;
	}
	
	public ArrayList<User> all(){
		ArrayList<User> users = userRepo.findAll();
		ArrayList<User> uUsers = new ArrayList<User>();
		for (User user : users) {
			user.setPortfolio(null);
			uUsers.add(user);
		}
		return uUsers;
	}
	
	public User findById(long id) {
		return userRepo.findOne(id);
	}
	
	public User findByIdFront(Long id) {
		User user = userRepo.findOne(id);
		user.setPortfolio(null);
		return user;
	}
	
	public Map<String, String>  delete(long id) {
		System.out.println("this is the id to delete"+ id);
		userRepo.delete(id);
		System.out.println("after deleteing");
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("success", "You successfully delete a user");
		return msg;
	}
}