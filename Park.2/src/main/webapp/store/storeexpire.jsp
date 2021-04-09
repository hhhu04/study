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

<% 
int n=0;
for(Cookie cookie : cookies)
{
	if(cookie.getName().equals("ID")) n++;
	
}

if(n == 0) {
	%>
		<script>
		alert("권한없음");
		$(location).attr('href','/Park/manager/manager.jsp');
		</script>
<% 
}
%>
<div class="box1">
<h4>가맹점 삭제</h4>


<form id="storefprm" method="post">

<input type="text" id="name" name="name" placeholder="가맹점명">
<input type="button"  id="add" value="등록">
<input type="button"  id ="main" value="취소">

</form>


</div>

<script>
$(function(){
	
 $('#add').on('click', function(e) {
	 var str = $("#storefprm").serialize();
 	var car = document.getElementById('name').value;
 	console.log(car);
 	console.log(str);
 	$.ajax({
        	url:'../StoreExpireControl',
        	type:'POST',
        	data : str,
        	dataType : 'text',
        	success : function(data){
				console.log(data);
				if($.trim(data) == "-1") alert("가맹점 확인");
				else if($.trim(data) == "0"){
					alert("가맹점 삭제 완료");
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


<script>
$(function(){
 $('#main').on('click', function(e) {
	 $(location).attr('href','/Park/manager/manager.jsp');
	});
}); 
</script>















</body>
</html>