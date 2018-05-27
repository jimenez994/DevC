package com.zeus.DevC.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeus.DevC.Models.Education;
import com.zeus.DevC.Models.Experience;
import com.zeus.DevC.Models.Portfolio;
import com.zeus.DevC.Models.User;
import com.zeus.DevC.Repositories.PortfolioRepository;

@Service
public class PortfolioService {
	@Autowired 
	private PortfolioRepository _pR;
	
	@Autowired
	private UserService _uS;
	
	ObjectMapper oMapper = new ObjectMapper();
	
//	Portfolio CRUD
	public Map<String, String> create(Portfolio portfolio, User user){
		Map<String,String> msg = new HashMap<String, String>();
		Portfolio checkP = _pR.findByUserId(user.getId());
		if(checkP == null) {
			if(portfolio.getHandle().isEmpty()) {
				msg.put("handle", "Handle is required");
			}else if(_pR.findByHandle(portfolio.getHandle()) != null) {
				msg.put("handle", "Handle is already taken");
			}
			if(portfolio.getStatus().isEmpty()) {
				msg.put("status", "Status is required");
			}
			if(portfolio.getSkills().isEmpty()) {
				msg.put("skills", "Skills is required");
			}
			if(msg.isEmpty()) {
				portfolio.setUser(user);
				user.setPortfolio(portfolio);
				_uS.update(user);
				msg.put("success", "Created a porfolio");
			}
			return msg;
		}
		msg.put("fail", "You already have a portfolio");
		return msg;
	}
	public Portfolio userPorfolio(long id) {
		return _pR.findByUserId(id);
	}
	
	public Portfolio findByHandle(String handle) {
		return _pR.findByHandle(handle);
	}
	public Portfolio findById(long id) {
		Portfolio portfolio = _pR.findOne(id);;
		return portfolio;
	}
	public Map<String,String> update(Portfolio portfolio) {
		Map<String,String> msg = new HashMap<String, String>();
		
		Portfolio checkPorfolio = _pR.findByHandle(portfolio.getHandle());
		
		if(portfolio.getHandle().isEmpty()) {
			msg.put("handle", "Handle is required");
			return msg;
		}
		if(checkPorfolio != null) {
			if(checkPorfolio.getHandle() != portfolio.getUser().getPortfolio().getHandle()) {
				msg.put("handle", "Handle is already taken");
				return msg;
			}
		}
		_pR.save(portfolio);
		msg.put("success", "You have update your portfolio");
		return msg;
	}
	
	public ArrayList<Portfolio> all(){
		return _pR.findAll();
	}
}