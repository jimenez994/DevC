package com.zeus.DevC.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeus.DevC.Models.Education;
import com.zeus.DevC.Models.Portfolio;
import com.zeus.DevC.Models.User;
import com.zeus.DevC.Services.EducationService;
import com.zeus.DevC.Services.PortfolioService;
import com.zeus.DevC.Services.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/edu")
public class EducationController {
	
	@Autowired
	private EducationService _eduS;
	
	@Autowired
	private UserService _Us;
	
	@Autowired
	private PortfolioService _Ps;
	
	@RequestMapping("/new/{id}")
	public Map<String, String> newEducation(@RequestBody Education edu, @PathVariable long id){
		User user = _Us.findById(id);
		edu.setPortfolio(user.getPortfolio());
		return _eduS.createEdu(edu);
	}
	@DeleteMapping("/delete/{id}")
	public Portfolio deleteEdu(@PathVariable long id) {
		long userId = _eduS.findOne(id).getPortfolio().getUser().getId();
		_eduS.deleteEdu(id);
		return _Ps.userPorfolio(userId);
	}

}