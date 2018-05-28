package com.zeus.DevC.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeus.DevC.Models.Post;
import com.zeus.DevC.Models.User;
import com.zeus.DevC.Repositories.PostRepository;
import com.zeus.DevC.Repositories.UserRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository _pR;
	
	@Autowired 
	private UserRepository _uR;
	
    ObjectMapper oMapper = new ObjectMapper();
	
	public Map<String,Object> create(Post post) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(post.getText() == null || post.getText() == "") {
			map.put("text", "Text is required");
		}else {
			Post newPost = _pR.save(post);
			map = oMapper.convertValue(newPost, Map.class);
			map.put("success", "Created a Post");
		}
		return map;
	}
	public ArrayList<Post> findAll(){
		return _pR.findAll();
	}
	
	public Map<String, String> delete(long id){
		Map<String, String> msg = new HashMap<String, String>();
		_pR.delete(id);
		msg.put("success", "successfully deleted a post");
		return msg;
	}
	
	public ArrayList<Post> likePost(long postId, long userId){
		Post post = _pR.findOne(postId);
		User user = _uR.findOne(userId);
		if(!post.getLikes().contains(user)) {
			List<User> likes = post.getLikes();
			likes.add(user);
			_pR.save(post);
		}
		return _pR.findAll();
	}
	public Map<String, String> unlikePost(long postId, long userId){
		Post post = _pR.findOne(postId);
		User user = _uR.findOne(userId);
		if(post.getLikes().contains(user)) {
			post.getLikes().remove(user);
			_pR.save(post);
		}
		return null;
	}
	
	public Post findOne(long id) {
		return _pR.findOne(id);
	}


}
