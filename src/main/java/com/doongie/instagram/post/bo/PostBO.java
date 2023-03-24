package com.doongie.instagram.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doongie.instagram.common.FileManagerService;
import com.doongie.instagram.post.dao.PostDAO;
import com.doongie.instagram.post.model.Post;
import com.doongie.instagram.post.model.PostDetail;
import com.doongie.instagram.user.bo.UserBO;
import com.doongie.instagram.user.model.User;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private UserBO userBO;
	
	public int addPost(
			//filemanager에서 필요하니 원래 없던 int userId를 넣음
			int userId
			, String content
			, MultipartFile file) {
		
		
		// 파일의 경로를 얻어내는 과정
		String imagePath = FileManagerService.saveFile(userId, file);
		
		return postDAO.insertPost(userId, content, imagePath);
		
	}
	
	public List<PostDetail> getPostList() {
		
		// 컨트롤러에서 원하는 데이터 형태를 만들어낸다. 
		// 이게 BO의 역할
		List<Post> postList = postDAO.selectPostList();
		
		List<PostDetail> postDetailList = new ArrayList<>();
		
		for(Post post:postList) {
			
			
			User user = userBO.getUserById(post.getUserId());
			
			PostDetail postDetail = new PostDetail();
			
			postDetail.setId(post.getId());
			postDetail.setContent(post.getContent());
			postDetail.setImagePath(post.getImagePath());
			postDetail.setUserId(post.getUserId());
			postDetail.setLoginId(user.getName());
			
			postDetailList.add(postDetail);
			
		}
		
		return  postDetailList;
		
		
		// return postDAO.selectPostList();
	}

}
