package com.zeus.DevC.Repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.zeus.DevC.Models.Education;

public interface EducationRepository extends CrudRepository<Education, Long> {
	ArrayList<Education> findAll();
}
