package com.doongie.instagram.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.doongie.instagram.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/create")
	public Map<String, String> postCreate(
			@RequestParam("content") String content
			, @RequestParam(value="file", required=false) MultipartFile file
			, HttpSession session) {
		
		// userId는 session에서 받아오는거임?? 어떻게????
		int userId = (Integer)session.getAttribute("userId");
		
		
		int count = postBO.addPost(userId, content, file);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	

}
