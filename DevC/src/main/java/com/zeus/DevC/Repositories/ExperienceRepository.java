package com.zeus.DevC.Repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.zeus.DevC.Models.Experience;

public interface ExperienceRepository extends CrudRepository<Experience, Long> {
	ArrayList<Experience> findAll();
}
