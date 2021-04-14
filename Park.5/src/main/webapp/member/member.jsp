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

<div class="box2">

<h4>정기주차 가입/갱신</h4>
<form id="memberform" method="POST">
<br>
<p><input type="text" id="car_number" name="car_number">&nbsp;차량 번호</p>
<p><input type="radio" id="check" name="check" value="1">1주 주차	&nbsp;
<input type="radio" id="check" name="check" value="2">1개월 주차	&nbsp;
<input type="radio" id="check" name="check" value="3">3개월 주차</p>
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
</select></p>
<br>
<p>카드정보</p>
<input type="month" id="month" name="month" placeholder="유효월"><br>
<input type="text" placeholder="카드번호" id ="number" name="number"><br>
<input type="button" id ="add" value="결제"><input type="button" id ="main" value="취소">
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
	 var car = document.getElementById('car_number').value;
	 var car2 = document.getElementById('check').value;
	 var car3 = document.getElementById('card').value;
	 var car5 = document.getElementById('month').value;
	 var car6 = document.getElementById('number').value;
	
	var str = $("#memberform").serialize();
		 
 	$.ajax({
        	url:'../MemberControl',
        	type:'POST',
        	data : str,
        	dataType : 'text',
        	success : function(data){
				console.log(data);
				if($.trim(data) == "0000") alert("잔액부족");
				if($.trim(data) == "3") alert("차번 확인");
				if($.trim(data) == "7") alert("입력 항목 체크");
				if($.trim(data) == "carNum") alert("차번호 확인");
				if($.trim(data) == "cardNum") alert("카드번호 확인");
				if(($.trim(data) == "0")) {
					alert("가입/갱신 완료");
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