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

<h4>직원 해고</h4>

<form id="expireform" class="box2" method="POST">
<input type="text" id="id" name="id" placeholder="직원 ID">
<input type="button" id="add" value="삭제">
</form>

</div>

<script>
$(function(){
	
 $('#add').on('click', function(e) {
 	var str = $("#expireform").serialize();
 	//var car = document.getElementById('car_number').value;
 	$.ajax({
        	url:'../ManagerExpireControl',
        	type:'POST',
        	data : str,
        	dataType : 'text',
        	success : function(data){
				console.log(data);
				if($.trim(data) == "-1") alert("ID 확인");
				if($.trim(data) == "9") alert("사장 ID");
				else if($.trim(data) == "0"){
					alert("삭제 완료");
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






<style>
.box2 {
	text-align:left;
	padding-left : 20%;
	padding-top: 5%;
	margin-left: 20%;
	}
</style>


</body>
</html>