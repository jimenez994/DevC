package com.zeus.DevC.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeus.DevC.Models.Post;
import com.zeus.DevC.Repositories.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository _pR;
	
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

}
