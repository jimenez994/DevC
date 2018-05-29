package com.zeus.DevC.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public Map<String,Object> createPost(@RequestBody Post post) {
		return _pS.create(post);
	}
	
	@RequestMapping("/get/{id}")
	public Post getPost(@PathVariable("id") long id) {
		return _pS.findOne(id);
	}
	
	@RequestMapping("/all")
	public List<Post> allPosts(){
		return _pS.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public Map<String,String> deletePost(@PathVariable("id") long id){
		return _pS.delete(id);
	}
	
	@PostMapping("/like/{postId}/{userId}")
	public List<Post> likePost(@PathVariable("postId") long pId, @PathVariable("userId") long uId){
		return _pS.likePost(pId, uId);
	}
	
	@PostMapping("/unlike/{postId}/{userId}")
	public Map<String,String> unlikePost(@PathVariable("postId") long pId, @PathVariable("userId") long uId){
		return _pS.unlikePost(pId, uId);
	}
	

}
