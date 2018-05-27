package com.zeus.DevC.Services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.DevC.Models.Education;
import com.zeus.DevC.Repositories.EducationRepository;

@Service
public class EducationService {
	
	@Autowired
	private EducationRepository _eduR;
	
	public Map<String, String> createEdu(Education edu){
		Map<String, String> msg = new HashMap<String, String>();
		if(edu.getSchool() == "" || edu.getSchool() == null) {
			msg.put("school", "School cannot be empty");
		}
		if(edu.getDegree() == "" || edu.getDegree() == null) {
			msg.put("degree", "Degree cannot be empty");
		}
		if(edu.getStartDate() == null) {
			msg.put("startDate", "Start from cannot be empty");
		}
		if(msg.isEmpty()) {
			_eduR.save(edu);
			msg.put("success", "You successfully created an education");
			return msg;
		}
		return msg;
	}
	public void deleteEdu(long id){
		_eduR.delete(id);
	}
	public Education findOne(long id) {
		return _eduR.findOne(id);
	}

}
