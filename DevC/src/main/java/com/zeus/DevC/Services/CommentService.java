package com.zeus.DevC.Services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeus.DevC.Models.Comment;
import com.zeus.DevC.Models.Post;
import com.zeus.DevC.Repositories.CommentRepository;
import com.zeus.DevC.Repositories.PostRepository;

@Service
public class CommentService {

	@Autowired 
	private CommentRepository _cR;
	
	@Autowired 
	private PostRepository _pR;
	
    ObjectMapper oMapper = new ObjectMapper();
	
	public Map<String,Object> create(Comment comment, long postId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(comment.getText() == null | comment.getText() == "") {
			map.put("text", "Text is required");
		}else {
			Post post = _pR.findOne(postId);
			comment.setPost(post);
			Comment newComment = _cR.save(comment);
			post.getComments().add(newComment);
			_pR.save(post);
			map = oMapper.convertValue(newComment, Map.class);
			map.put("success", "Created a comment");
		}
		return map;
	}
	public Map<String,Object> deleteById(long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		_cR.delete(id);
		map.put("success", "successfully delete comment");
		return map;
	}
}
