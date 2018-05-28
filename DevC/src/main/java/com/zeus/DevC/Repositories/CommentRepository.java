package com.zeus.DevC.Repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.zeus.DevC.Models.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	ArrayList<Comment> findAll();

}
