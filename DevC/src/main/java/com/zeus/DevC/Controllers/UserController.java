package com.zeus.DevC.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeus.DevC.Models.User;
import com.zeus.DevC.Services.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService _userS;

	@GetMapping("/all")
	public ArrayList<User> all(){
		return _userS.all();
	}
	
	@PostMapping("/register")
	public Map<String, String> create(@RequestBody User user,HttpSession session){
		Map<String, String> res = _userS.create(user);
		if(res.get("user_id") != null) {
			session.setAttribute("user_id", Long.parseLong(res.get("user_id")));
		}
		return res;
	}
	
	@PostMapping("/login")
	public Map<String, String> login(@RequestBody User user,HttpSession session){
		Map<String, String> res = _userS.login(user.getEmail(), user.getPassword());
		if(res.get("user_id") != null) {
			session.setAttribute("user_id", Long.parseLong(res.get("user_id")));
			System.out.println(session.getAttribute("user_id") + " * **************5");
		}
		return res;
	}
	
	@GetMapping("/find/{id}")
	public User user(@PathVariable("id") Long id) {
		return _userS.findById(id);
	}
	
	@PutMapping("/update")
	public Map<String, String> update(@RequestBody User user){
		return _userS.update(user);
	}
	
	@DeleteMapping("/delete/{id}")
	public Map<String, String> deleteUser(@PathVariable("id") Long id){
		return _userS.delete(id);
	}
	@RequestMapping("/session")
	public Map<String, String> check(HttpSession session){
		Map<String,String> msg = new HashMap<String, String>();
		if(session.getAttribute("user_id") != null) {
			msg.put("user_id", session.getAttribute("user_id")+"");
			return msg;
		}
		msg.put("user_id", null);
		return msg;
	}
	
	@DeleteMapping("/logout")
	public Map<String, String> logout(HttpSession session) {
		Map<String,String> msg = new HashMap<String, String>();
		session.setAttribute("user_id", null);
		msg.put("message", "You successfully logout");
		return msg;
	}
}
