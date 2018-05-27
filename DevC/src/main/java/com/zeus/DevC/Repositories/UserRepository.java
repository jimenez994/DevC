package com.zeus.DevC.Repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.zeus.DevC.Models.User;

	public interface UserRepository extends CrudRepository<User, Long> {
		ArrayList<User> findAll();
		User findByEmail(String email);
}
