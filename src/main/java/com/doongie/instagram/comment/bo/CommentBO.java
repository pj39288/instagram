package com.doongie.instagram.comment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doongie.instagram.comment.dao.CommentDAO;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;

	public int addComment(
			int userId
			, int postId
			, String comment) {
		
		return commentDAO.insertComment(userId, postId, comment);
		
	}
}
