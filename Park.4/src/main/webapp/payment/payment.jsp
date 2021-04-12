<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="payment.PaymentControl" %>

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
String carNumber = null;;
String expected = null;
int num = 0;
for(Cookie cookie : cookies) {
	if(cookie.getName().equals("car_number")) carNumber = cookie.getValue();
	if(cookie.getName().equals("pay")) {
		expected = cookie.getValue();
		num++;
	}
}
if(num == 0){
%>
location.href = "/Park/payment/checkcar.jsp";
<%}%>
</script>


<div class="box2">
<h4>현금 불가</h4>
<p><%= carNumber %> 님이 결제할 금액 : <%= expected %> 원 </p>
<form id="payform">
<input type="hidden" name= "car_number" value="<%= carNumber %>">
<p>사용할 카드
<select name="card" id="card">
<option>==선택==</option>
<option>신한</option>
<option>국민</option>
<option>현대</option>
</select>
할부
<select name="hal" id = "hal">
<option>1</option>
<option>2</option>
<option>3</option>
<option>4</option>
<option>5</option>
<option>6</option>
<option>7</option>
<option>8</option>
<option>9</option>
<option>10</option>
<option>11</option>
<option>12</option>
</select></p><br>
<p>할인바코드 
<input type="text" name="dis"></p>
<br>
<p>카드정보</p>
<input type="month" id="month" name="month" placeholder="유효월"><br>
<input type="text" placeholder="카드번호" id ="number" name="number" maxlength="16"><br>
<input type="button" id ="add" value="결제"><input type="button" id ="main" value="취소">
<br>
</form>
</div>
<style>
.box2 {
	text-align:left;
	padding-left : 20%;
	padding-top: 5%;
	margin-left: 20%;
	}
</style>
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
 	var str = $("#payform").serialize();
 	$.ajax({
        	url:'../PaymentControl',
        	type:'POST',
        	data : str,
        	dataType : 'text',
        	success : function(data){
        			console.log(data);
        			if($.trim(data) == "0000") alert("결제오류!"); 
        			else if($.trim(data) == "x") {
        				alert("해당 차량은 이미 결제하였거나 잘못된 차량번호입니다.");
        				$(location).attr('href','/Park/main.jsp');	
        			}
        			else if($.trim(data) == "7") {
        				alert("입력값 확인");
        			}
        			else if($.trim(data) == "mmm") {
        				alert("등록되지 않은 가맹점");
        			}
        			else if($.trim(data) == "111") {
        				alert("할인적용 결제완료");
        				$(location).attr('href','/Park/main.jsp');
        			}
        			else if($.trim(data) == "carNum") {
        				alert("차량번호를 확인하세요");
        			}
        			else if($.trim(data) == "cardNum") {
        				alert("카드번호를 확인하세요");
        			}
       
        			
        			else {
        				alert("결제완료");	
        				$(location).attr('href','/Park/main.jsp');	
        			}
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