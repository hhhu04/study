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

<h4>차량번호 입력</h4>
<form id="checkform" method="POST">
<input type="text" name="number">
<input type="button" id="add" value="입력"><input type="button" id ="main" value="홈">
<br>
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
 	var str = $("#checkform").serialize();
 	$.ajax({
        	url:'../CheckControl',
        	type:'POST',
        	data : str,
        	dataType : 'text',
        	success : function(data){
        			console.log(data);
        			if($.trim(data) == "member") alert("정기 차량 입니다"); 
        			else if($.trim(data) == "fail") alert("차량번호 확인"); 
        			else if($.trim(data) == "carNum") alert("차량번호 확인");
        			else $(location).attr('href','/Park/payment/payment.jsp');	
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