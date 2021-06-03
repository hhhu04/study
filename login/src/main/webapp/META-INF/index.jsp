<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>LoginTest</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./source/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./source/miniCss.css">
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
<h2>기본 페이지</h2>
<p><button class="btn btn-primary" onclick="location.href='login.jsp'">로그인</button> <button class="btn btn-primary" onclick="location.href='join.jsp'">회원가입</button></p>

</div>


</body>
</html>