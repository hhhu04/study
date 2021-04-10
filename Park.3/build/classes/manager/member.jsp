<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<br><br><br>
<div class="box1">

<h3>정기 주차 가입/갱신</h3>

<form id="memberform" method="POST">
<p>차번&nbsp;:	&nbsp;<input type="text" id="car" name="car">	&nbsp;	&nbsp;
<p>
<label><input type="radio" id="check" name ="check" value="1">1주 주차 	&nbsp;</label>
<label><input type="radio" id="check" name ="check" value="2">1개월 주차	&nbsp; </label>
<label><input type="radio" id="check" name ="check" value="3">3개월 주차	&nbsp;</label></p>
<p>사용할 카드
<select name="card" id="card">
<option>==선택==</option>
<option>신한</option>
<option>국민</option>
<option>현대</option>
</select></p>
<br>
<p>할부
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

<p>카드정보</p>
<input type="month" placeholder="유효월"><br>
<input type="text" placeholder="카드번호" id ="number" name="number"><br><br>
<input type="button" id="add" value="가입" class="btn btn-primary">

</form>
</div>

<script>
$(function(){
	
 $('#add').on('click', function(e) {
 	var str = $("#memberform").serialize();
 	$.ajax({
 		
        	url:'../MemberControl',
        	type:'POST',
        	data : str,
        	dataType : 'text',
        	success : function(data){
				console.log(data);
				if($.trim(data) == "0000") alert("결제오류!");
				else if($.trim(data) == "0"){
					alert("가입 성공");
					$(location).attr('href','/Park/main.jsp');
				}
				else if($.trim(data) == 3) alert("차번없음");
				else alert("실패");
				
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