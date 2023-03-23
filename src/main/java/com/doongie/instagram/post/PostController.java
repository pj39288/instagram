package com.doongie.instagram.post;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doongie.instagram.post.bo.PostBO;
import com.doongie.instagram.post.model.Post;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/list/view")
	public String instagramList(Model model) {
				
		List<Post> postList = postBO.getPostList();
		
		model.addAttribute("postList", postList);
		
		return "/post/list";
	}
	

}
