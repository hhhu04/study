<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "setting.Park" %>
<%@ page import = "setting.ParkControl" %>
<%@ page import = "setting.MemberControl" %>
<%@ page import = "setting.Ser" %>
<%@ page import = "price.Connect" %>
<!DOCTYPE html>
<html>
<head>
<% 
MemberControl mem = new MemberControl();

Connect conn = new Connect();
mem.setTime();
ParkControl pc = new ParkControl();
int seat = pc.readSetting();
System.out.println(seat);
String mm = Ser.date3();

%>


<% 
Cookie[] cookies = request.getCookies();
if(cookies.length > 0 ){
	for(Cookie cookie : cookies) {
		if(cookie.getName().equals("car_number")) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		if(cookie.getName().equals("pay")) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		if(cookie.getName().equals("ID")) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		if(cookie.getName().equals("car")) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
}
%>


<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./source/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./source/miniCss.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>주차장</title>
</head>
<body>

<div class="box1">
<br><br><br>
<h2>주차장 메인 화면</h2>
<br>
<h4>현재 잔여공간 : <%= seat %>석</h4>
<button class="btn btn-primary" onclick="location.href='payment/checkcar.jsp'">주차요금 정산</button><br><br>
<button class="btn btn-primary" onclick="location.href='member/member.jsp'">정기주차 가입</button><br><br>
<button class="btn btn-primary" onclick="location.href='manager/managerlogin.jsp'">관리자 메뉴</button>
<br><br><br><br>

<h5>번호판 스캔</h5>
<%if(seat > 0) {%>
<p><button class="btn btn-primary" onclick="location.href='inCar.jsp'">입차</button>
<% }%>
<button class="btn btn-primary" onclick="location.href='outcar.jsp'">출차</button><p>

</div>




</body>
</html>