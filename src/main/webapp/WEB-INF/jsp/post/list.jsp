<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<link rel="stylesheet" href="/static/css/style.css" type="text/css">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">



</head>
<body>

	<div id="wrap"> 
		<c:import url="/WEB-INF/jsp/include/header.jsp"></c:import>
		
		<section class="contents d-felx justify-content-center">
	
			
			<%-- content 입력화면 --%>
			<div class="input-box">		
				<textarea id="contentInput" rows="4" class="form-control"></textarea>	
				<div class="d-flex justify-content-between">
					<input type="file" id="fileInput">
					<button type="button" id="inputBtn">업로드</button>				
				</div>
			</div>
			
			
			
			<%-- 게시물 리스트 --%>
			<div class="card-list">
			
			<c:forEach var="post" items="${postList }">
			
				<%-- 게시물 하나 --%>
				<div class="card">
					<div class="d-flex justify-content-between">
						<div>doongie</div>
						<i class="bi bi-three-dots"></i>
					</div>
					
					<div>
						<img width="100%" src="${post.imagePath }">
					</div>
					
					<div>
						<i class="bi bi-suit-heart"></i> 좋아요 n개
					</div>
					
					<div>
						<b>${post.userId }</b> ${post.content }
					</div>
					
					<!-- 댓글 박스 -->
					<div>
						<div>댓글</div>
						
						<div><b>hagulu</b>진짜 이쁘네</div>
						<div><b>oss</b>뭐해</div>
						
						<div class="d-flex">
							<input type="text" class="form-control">
							<button type="button">게시</button>
						</div>
						
					</div>
				
				</div>
				
			</c:forEach>
			
			
			</div>
		
		
		</section>
		
		
		
		<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>	
	</div>
	
	<script>
		$(document).ready(function(){
			
			$("#inputBtn").on("click", function(){
				
				let content = $("#contentInput").val();
				
				let file = $("#fileInput")[0];

				if(content == ""){
					alert("내용을 입력하세요");
					return;
				}
				
				$.ajax({
					
					type:"get"
					, url:"/post/create"
					, data:{"content":content, "file":file.files[0]}
					, success:function(data){
						if(data.result == "success"){
							location.href="/post/list/view"
						} else {
							alert("내용 입력을 확인하세요");
						}
					}
					, error:function(){
						alert("게시 에러")
					}
					
					
				});
				
			});
			
		});
	
	
	
	</script>

</body>
</html>