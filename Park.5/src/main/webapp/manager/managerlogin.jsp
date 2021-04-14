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

<div class="box1">
<br><br><br>
<h3>관리자 페이지 입니다.</h3>
<form id="maform" method="post">
<input type="text" id="id" name="id" placeholder="관리자 ID">
<input type="password" id="pass" name="pass" placeholder="관리자 pw">
<input type="button" id="add" value="로그인">
<input type="button" id ="main" value="취소">
</form>

</div>

<script>
$(function(){
 $('#main').on('click', function(e) {
	 $(location).attr('href','/Park/main.jsp');
	});
}); 
</script>


<script>
$(function(){
	
 $('#add').on('click', function(e) {
 	var str = $("#maform").serialize();
 	var em = document.getElementById('id').value;
 	$.ajax({
 		
        	url:'../ManagerLoginControl',
        	type:'POST',
        	data : str,
        	dataType : 'text',
        	success : function(data){
				console.log(data);
				if($.trim(data) == em)$(location).attr('href','/Park/manager/manager.jsp');
    			else alert("아이디/패스워드 확인");
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