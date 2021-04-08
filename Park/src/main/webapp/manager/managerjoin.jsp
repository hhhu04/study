<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../source/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../source/miniCss.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Cookie[] cookies = request.getCookies();
String mail;
for(Cookie cookie : cookies)
{
	if(cookie.getName().equals("ID")) {
		if(!cookie.getValue().equals("123")) {%>
		<script>
		alert("권한없음");
		$(location).attr('href','/Park/manager/manager.jsp');
		</script>
<% 
		}
	}
}
%>


<div class="box1">

<h4>관리자 로그인</h4>
<form id = "managerform" method="POST">
<input type="email" id="email" name = "email" placeholder="관리자ID"><br>
<input type="password" id="pass" name = "pass" placeholder="비밀번호"><br>
<input type="text" id="name" name = "name" placeholder="이름"><br>
<input type="text" id="phone" name = "phone" placeholder="핸드폰"><br>
<input type="button" id = "add" value="가입">
</form>

</div>

<script>
$(function(){
	
 $('#add').on('click', function(e) {
 	var str = $("#managerform").serialize();
 	var email = document.getElementById('email').value;
 	var pas = document.getElementById('pass').value;
 	
 	$.ajax({
        	url:'../ManagerJoinControl',
        	type:'POST',
        	data : str,
        	dataType : 'text',
        	success : function(data){
        			console.log(data);
        			if($.trim(data) == email) alert("이미 존재하는 ID"); 
        			
        			else {
        				alert("가입 완료");
        				$(location).attr('href','/Park/manager/manager.jsp');
        			}	
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