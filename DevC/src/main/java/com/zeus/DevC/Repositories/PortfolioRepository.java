package com.zeus.DevC.Repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.zeus.DevC.Models.Portfolio;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {
	ArrayList<Portfolio> findAll();
	Portfolio findByHandle(String handle);
	Portfolio findByUserId(long id);
}
