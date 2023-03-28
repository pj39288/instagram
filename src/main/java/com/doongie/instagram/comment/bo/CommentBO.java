package com.doongie.instagram.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doongie.instagram.comment.dao.CommentDAO;
import com.doongie.instagram.comment.model.Comment;

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
	
	public List<Comment> showComment(int postId) {
		return commentDAO.selectCommentList(postId);
	}
	
	
}
