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
<script>

<%
Cookie[] cookies = request.getCookies();
String mail;
for(Cookie cookie : cookies)
{
	if(cookie.getName().equals("ID")) {
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
%>
alert("logout");

location.href = "/Park/main.jsp";

$(location).attr('href','/Park/main.jsp');

</script>
</body>
</html>