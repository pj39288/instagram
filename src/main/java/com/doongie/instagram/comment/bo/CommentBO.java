package com.doongie.instagram.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doongie.instagram.comment.dao.CommentDAO;
import com.doongie.instagram.comment.model.Comment;
import com.doongie.instagram.comment.model.CommentDetail;
import com.doongie.instagram.user.bo.UserBO;
import com.doongie.instagram.user.model.User;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserBO userBO;

	public int addComment(
			int userId
			, int postId
			, String comment) {
		
		return commentDAO.insertComment(userId, postId, comment);	
	}
	
	public List<CommentDetail> showComment(int postId) {
		
		List<Comment> commentList= commentDAO.selectCommentList(postId);
		
		List<CommentDetail> commentDetailList = new ArrayList<>();
		
		for(Comment comment:commentList) {
			
			User user = userBO.getUserById(comment.getUserId());
			
			CommentDetail commentDetail = new CommentDetail();
			
			commentDetail.setId(comment.getId());
			commentDetail.setUserId(comment.getUserId());
			commentDetail.setUserName(user.getLoginId());
			commentDetail.setContent(comment.getContent());
			
			commentDetailList.add(commentDetail);
		
		}
		
		return commentDetailList;
	}
	
	public int deleteCommentByPostId(int postId) {
		
		return commentDAO.deleteCommentByPostId(postId);
		
	}
	
	
}
