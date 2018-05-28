package com.zeus.DevC.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeus.DevC.Models.Comment;
import com.zeus.DevC.Services.CommentService;
import com.zeus.DevC.Services.PostService;

@RestController
@CrossOrigin("*")
@RequestMapping("/post/comment")
public class CommentController {
	
	@Autowired 
	private CommentService _cS;
	
	@Autowired
	private PostService _pS;
	
	@PostMapping("/new/{id}")
	public Map<String,Object> createComment(@PathVariable("id") long postId,@RequestBody Comment comment){
		return _cS.create(comment, postId);
	}
	
	@DeleteMapping("/delete/{id}")
	public Map<String,Object> deleteComment(@PathVariable("id") long id){
		return _cS.deleteById(id);
	}

}
