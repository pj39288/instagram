package com.doongie.instagram.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doongie.instagram.comment.bo.CommentBO;
import com.doongie.instagram.comment.model.CommentDetail;
import com.doongie.instagram.common.FileManagerService;
import com.doongie.instagram.like.bo.LikeBO;
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
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	public int addPost(
			//filemanager에서 필요하니 원래 없던 int userId를 넣음
			int userId
			, String content
			, MultipartFile file) {
		
		
		// 파일의 경로를 얻어내는 과정
		String imagePath = FileManagerService.saveFile(userId, file);
		
		return postDAO.insertPost(userId, content, imagePath);
		
	}
	
	public List<PostDetail> getPostList(int userId) {
		
		// 컨트롤러에서 원하는 데이터 형태를 만들어낸다. 
		// 이게 BO의 역할
		List<Post> postList = postDAO.selectPostList();
		
		List<PostDetail> postDetailList = new ArrayList<>();
		
		for(Post post:postList) {
			
			User user = userBO.getUserById(post.getUserId());
			
			int likeCount = likeBO.getLikeCount(post.getId());
			
			boolean isLike = likeBO.isLike(userId, post.getId());
			
			// commentBO.showComment(userId);
			List<CommentDetail> commentList = commentBO.showComment(post.getId());
			
			PostDetail postDetail = new PostDetail();
			
			postDetail.setId(post.getId());
			postDetail.setContent(post.getContent());
			postDetail.setImagePath(post.getImagePath());
			postDetail.setUserId(post.getUserId());
			postDetail.setLoginId(user.getName());
			postDetail.setLikeCount(likeCount);
			postDetail.setLike(isLike);
			postDetail.setCommentList(commentList);
			
			postDetailList.add(postDetail);
			
		}
		
		return  postDetailList;
		
		
		// return postDAO.selectPostList();
	}
	
	
	public int deletePost(int userId, int id) {
		
		// 이미지 도 삭제해야함
		// 이미지 경로 받아서 파일매니저에 보낸것
		// Post post = postDAO.selectPost(id);
		// FileManagerService.removeFile(post.getImagePath());
		
		
		// 댓글과 좋아요는 있을수도 없을수도 있기 때문에 그 결과값을 활용할 필요는 없다. 
		// 댓글 삭제
		commentBO.deleteCommentByPostId(id);
		
		// 좋아요 삭제
		likeBO.deleteLikeByPostId(id);
		
		
		return postDAO.deletePost(userId, id);
		
	}

}
