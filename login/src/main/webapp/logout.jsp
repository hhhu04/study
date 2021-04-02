<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>LoginTest</title>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
<%
Cookie[] cookies = request.getCookies();
String mail;
for(Cookie cookie : cookies)
{
	if(cookie.getName().equals("email")) {
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
%>
alert("logout");

location.href = "/login/index.jsp";

$(location).attr('href','/login/index.jsp');
</script>
</body>
</html>