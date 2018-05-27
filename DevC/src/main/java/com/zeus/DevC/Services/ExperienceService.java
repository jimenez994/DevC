package com.zeus.DevC.Services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.DevC.Models.Experience;
import com.zeus.DevC.Repositories.ExperienceRepository;

@Service
public class ExperienceService {
	
	@Autowired
	private ExperienceRepository _Er;
	
	public Map<String, String> createExp(Experience exp){
		Map<String, String> msg = new HashMap<String, String>();
		if(exp.getTitle() == "" || exp.getTitle() == null) {
			msg.put("title", "Title cannot be empty");
		}
		if(exp.getCompany() == "" || exp.getCompany() == null) {
			msg.put("company", "Company cannot be empty");
		}
		if(exp.getStartDate() == null) {
			msg.put("startFrom", "Start from cannot be empty");
		}
		if(msg.isEmpty()) {
			_Er.save(exp);
			msg.put("success", "You successfully created an experience");
			return msg;
		}
		return msg;
	}
	public void deleteExp(long id){
		_Er.delete(id);
	}
	
	public Experience findOne(long id) {
		return _Er.findOne(id);
	}

}