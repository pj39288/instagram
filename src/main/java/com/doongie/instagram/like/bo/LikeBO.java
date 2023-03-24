package com.doongie.instagram.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doongie.instagram.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public int setLike(int userId, int postId) {
		
		return likeDAO.insertLike(userId, postId);
		
	}

}
