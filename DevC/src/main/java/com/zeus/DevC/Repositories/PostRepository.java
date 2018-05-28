package com.zeus.DevC.Repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.zeus.DevC.Models.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
	ArrayList<Post> findAll();

}
