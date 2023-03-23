package com.doongie.instagram.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doongie.instagram.post.dao.PostDAO;
import com.doongie.instagram.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String content) {
		
		return postDAO.insertPost(userId, content);
		
	}
	
	public List<Post> getPost() {
		
		return postDAO.selectAllPost();
	}

}
