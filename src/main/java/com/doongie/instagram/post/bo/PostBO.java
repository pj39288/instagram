package com.doongie.instagram.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doongie.instagram.common.FileManagerService;
import com.doongie.instagram.post.dao.PostDAO;
import com.doongie.instagram.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	public int addPost(
			int userId
			, String content
			, MultipartFile file) {
		
		String imagePath = FileManagerService.saveFile(userId, file);
		
		return postDAO.insertPost(userId, content, imagePath);
		
	}
	
	public List<Post> getPostList() {
		
		return postDAO.selectPostList();
	}

}
