<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="managerlist.ManagerListControl" %>   
<%@ page import = "setting.MemberControl" %>
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
MemberControl mem = new MemberControl();
String mail;
int num=0;
String master = "0";
for(Cookie cookie : cookies)
{

	if(cookie.getName().equals("ID")) {
		if(cookie.getValue().equals("123")){
			master = cookie.getValue();
			cookie.setMaxAge(999);
			response.addCookie(cookie);
		}
		num++;
	}
}

if(master.equals("123")) {mem.cc();}
if(num == 0){
%>
<script>
alert("권한없음");
$(location).attr('href','/Park/main.jsp');
</script>
<% 	
}
%>


<script>
setTimeout(function(){
location.reload();
},60000); 
</script>



<div class=box1>
<a href="managerj.jsp"><button id="adder" class="btn btn-primary" >관리자 페이지</button></a>
</div>






</body>
</html>