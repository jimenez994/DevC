package com.zeus.DevC.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.DevC.Models.Post;
import com.zeus.DevC.Repositories.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository _pR;
	
	public void create(Post post) {
		_pR.save(post);
	}

}
