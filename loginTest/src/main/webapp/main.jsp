<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.TreeMap" %>
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
Cookie[] coo = request.getCookies();
TreeMap<String,String> map = new TreeMap<>();
int num = 0;
for(Cookie cookie: coo){
	if(cookie.getName().equals("email")) {
		map.put(cookie.getName(),cookie.getValue());
		num++;
	}
}

if(num == 0)
{
	%>
	alert("login");
	$(location).attr('href','/loginTest/login.jsp');
	<%
}

%>


</script>
<div class = "box1">
<p>Hello <%= map.get("email") %></p>
<button class="btn btn-primary" id="add" name="add">로그아웃</button>
</div>

<script>
$(function(){
$('#add').on('click', function(e) {

$(location).attr('href','/loginTest/logout.jsp');
});
});
</script>

</body>
</html>