package com.zeus.DevC.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeus.DevC.Models.Post;
import com.zeus.DevC.Services.PostService;

@RestController
@CrossOrigin("*")
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService _pS;
	
	@PostMapping("/new")
	public String createPost(@RequestBody Post post) {
		_pS.create(post);
		return "success";
	}

}
