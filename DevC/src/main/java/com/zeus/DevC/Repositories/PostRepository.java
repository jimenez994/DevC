package com.zeus.DevC.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.zeus.DevC.Models.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
	@Query(value="SELECT * FROM post ORDER BY created_at DESC;", nativeQuery=true)
	List<Post> findAll();
//	ArrayList<Post> findAll();

}
