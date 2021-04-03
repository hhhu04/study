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



<div class = "box1">
<h3>로그인 페이지</h3>
<form id="logForm" method="GET">
<input type="email" id="email" name ="email" placeholder="이메일">
<input type="password" id="pass" name="pass" placeholder="비밀번호">
<input type="button" id="add" name="add" value="로그인">
</form>
<br>
<p><button class="btn btn-primary" onclick="location.href='join.jsp'">회원가입</button>&#32;&#32;<button class="btn btn-primary" onclick="location.href='index.jsp'">뒤로</button></p>
</div>

<script>
<%
Cookie[] cookies = request.getCookies();
int n = 0;
for(Cookie cookie : cookies) if(cookie.getName().equals("email")) n++;
if(n > 0){
%>
location.href = "/loginTest/main.jsp";
//$(location).attr('href','/login/main.jsp');
<%}%>

</script>
 

<script>
$(function(){
	
 $('#add').on('click', function(e) {
 	var str = $("#logForm").serialize();
 	var em = document.getElementById('email').value;
 	$.ajax({
        	url:'./LoginControl',
        	type:'POST',
        	data : str,
        	dataType : 'text',
        	success : function(data){
        			console.log(data);
        			if($.trim(data) == em)$(location).attr('href','/loginTest/main.jsp');
        			else alert("입력 값 확인");
        	},
        		error:function(data){
        			console.log(data);
        	}
        	
        });
 	});
}); 

</script>


</body>
</html>