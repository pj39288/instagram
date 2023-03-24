package com.doongie.instagram.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doongie.instagram.like.bo.LikeBO;

@RestController
public class LikeRestController {

	@Autowired
	private LikeBO likeBO;
	
	
	@GetMapping("/like")
	public Map<String, String> setLike(
			// int userId
			int postId
			, HttpSession session) {
		
		int userId = (Integer) session.getAttribute("userId");
		
		int count = likeBO.setLike(userId, postId);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		}  else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
}
