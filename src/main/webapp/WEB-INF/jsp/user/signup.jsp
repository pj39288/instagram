<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인스타그램 회원가입</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<link rel="stylesheet" href="/static/css/style.css" type="text/css">

</head>
<body>
	<div id="wrap">
		<header>
			<h1>doongiestagram</h1>
		</header>
		
		<br>
		
		<section class="contents">
			<div class="joinbox text-center">
				<div>
					<input type="text" id="loginIdInput" placeholder="Username">
					<button type="button" id="doubleBtn"> 중복확인</button>				
				</div>
				<input type="text" id="passwordInput" placeholder="....."> <br>
				<input type="text" id="passwordConfirmInput" placeholder="비밀번호 확인"> <br>
				<input type="text" id="nameInput" placeholder="이름"> <br>
				<input type="text" id="emailInput" placeholder="이메일"> <br>
				<button type="button" id="joinBtn">회원가입</button>				
			
			</div>
			
		
		</section>
		
		<footer class="text-center">
			<hr>
			<div>Copyright@ doongiestagram 2023</div>
		
		</footer>
	
	
	
	</div>
	
	<script>
		$(document).ready(function(){
			
			$("#joinBtn").on("click", function(){
				
				let loginId = $("#loginIdInput").val();		
				let password = $("#passwordInput").val();			
				let passwordConfirm = $("#passwordConfirmInput").val();			
				let name = $("#nameInput").val();			
				let email = $("#emailInput").val();
				
				
				if(loginId == ""){
					alert("id를 입력하세요");
					return;
				}
				
				if(password == ""){
					alert("비밀번호를 입력하세요");
					return;
				}
				
				if(passwordConfirm == ""){
					alert("비밀번호 확인을 입력하세요");
					return;
				}
				
				// alert("비밀번호: " password "비밀번호 확인 " passwordConfirm);
				
				if(password != passwordConfirm){
					alert("비밀번호가 일치하지 않습니다");
					return;
				}
				if(name == ""){
					alert("이름을 입력하세요");
					return;
				}
				if(email == ""){
					alert("이메일을 입력하세요");
					return;
				}
				
				
				.ajax({
					
					type:"post"
						, url:"/user/signup"
						, data:{"loginId":loginId, "password":password, "name":name, "email":email}
						, success:function(data){
							if(data.result == "success"){
								location.href = "/user/signin/view";
							} else {
								alert("회원가입 실패");
							}
						}
						, error:function(){
							alert("회원가입 에러");
							
						}
				
				});
				
				
				
			});
			
			
		});		
		
		
		
		
		
	</script>




</body>
</html>