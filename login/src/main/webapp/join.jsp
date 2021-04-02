<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>LoginTest</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./source/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./source/miniCss.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
<%
Cookie[] cookies = request.getCookies();
int n = 0;
for(Cookie cookie : cookies) if(cookie.getName().equals("email")) n++;
if(n > 0){
%>
location.href = "/login/main.jsp";
//$(location).attr('href','/login/main.jsp');
<%}%>

</script>

<div class="box1">
<h3>회원가입</h3>
<form id="inForm" method="POST">
<input type="email" name="email" id="email" placeholder="이메일 입력">
<input type="password" name="pass" id="pass" placeholder="비밀번호 입력">
<input type="button" id="add" name="add" value="가입">
</form>
<br>
<button class="btn btn-primary" onclick="location.href='index.jsp'">뒤로</button>
</div>

<script>
$(function(){
	
 $('#add').on('click', function(e) {
 	var str = $("#inForm").serialize();
 	var em = document.getElementById('email').value;
 //	console.log(em);
 	$.ajax({
 		
        	url:'./Control',
        	type:'POST',
        	data : str,
        	dataType : 'text',
        	success : function(data){
        		console.log(data);
				if($.trim(data) == em) alert(data+" 는 이미 존재하는 계정입니다!");
				else { $(location).attr('href','/login/login.jsp');}
        		
        	},
        		error:function(data){
        			console.log(data);
        			alter("가입 실패!");
        	}
        	
        });
 	
	
 });
}); 

</script>



</body>
</html>