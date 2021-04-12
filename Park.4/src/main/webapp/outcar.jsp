<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "setting.Park" %>
<%@ page import = "setting.ParkControl" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./source/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./source/miniCss.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class= box1>
<h3>출차 스캔대체 입력</h3>

<form id="outForm" method="POST">
<input type="text"  id="car_number" name="car_number" placeholder="차번">
<input type="button" value="입력" id="add">
</form>
<br>
<button class="btn btn-primary" onclick="location.href='main.jsp'">홈</button>
</div>
<script>
$(function(){
	
 $('#add').on('click', function(e) {
 	var str = $("#outForm").serialize();
 	var car = document.getElementById('car_number').value;
 	$.ajax({
 		
        	url:'./OutControl',
        	type:'POST',
        	data : str,
        	dataType : 'text',
        	success : function(data){
				console.log(data);
				if($.trim(data) == car){
					alert("출차!");
				
					$(location).attr('href','/Park/main.jsp');
				}
				else if($.trim(data) == "9") alert("이미 출차한 차량입니다.");
				else if($.trim(data) == "carNum") alert("형식에 맞는 차번을 입력하세요");
				else alert("결제상태나 정기 유효일을 확인하세요.");
        	},
        		error:function(data){
        			console.log(data);
        	}
        	
        });
 <!-- $(location).attr("href", "/pro/index.jsp"); -->	 
 });
}); 
 

</script>

</body>
</html>