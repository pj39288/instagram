package com.doongie.instagram.post;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doongie.instagram.comment.bo.CommentBO;
import com.doongie.instagram.post.bo.PostBO;
import com.doongie.instagram.post.model.PostDetail;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostBO postBO;
		
	@GetMapping("/list/view")
	public String instagramList(Model model, HttpSession session) {
		
		int userId = (Integer) session.getAttribute("userId");
		
		List<PostDetail> postList = postBO.getPostList(userId);
		
		model.addAttribute("postList", postList);
		
		return "/post/list";
	}
	
	
	

}
