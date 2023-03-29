package com.doongie.instagram.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.doongie.instagram.comment.bo.CommentBO;
import com.doongie.instagram.comment.model.CommentDetail;

public class CommentController {

	@Autowired
	private CommentBO commentBO;
	
	public String commentList(
			@RequestParam("postId") int postId
			, Model model) {
		
		List<CommentDetail> commentList = commentBO.showComment(postId);
		
		model.addAttribute("commentList", commentList);
		
		return "/post/list";		
		
	}

}
