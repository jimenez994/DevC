package com.zeus.DevC.Controllers;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeus.DevC.Models.Experience;
import com.zeus.DevC.Models.User;
import com.zeus.DevC.Services.ExperienceService;
import com.zeus.DevC.Services.PortfolioService;
import com.zeus.DevC.Services.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/exp")
public class ExperienceController {
	
	@Autowired 
	private ExperienceService _eS;
	
	@Autowired
	private UserService _Us;
	
	@Autowired
	private PortfolioService _Ps;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping("/new/{id}")
	public Map<String, String> newExperience(@RequestBody Experience exp, @PathVariable long id) throws ParseException{
		User user = _Us.findById(id);
		exp.setPortfolio(user.getPortfolio());
		return _eS.createExp(exp);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteExperience(@PathVariable long id) throws JsonProcessingException{
		long userId = _eS.findOne(id).getPortfolio().getUser().getId();
		_eS.deleteExp(id);
		return mapper.writeValueAsString(_Ps.userPorfolio(userId));
	}
}
