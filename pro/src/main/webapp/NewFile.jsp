<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>


<form name="testForm" id="login">
    <input type="text" id="id"/>
    <input type="text" id="pw"/>
    <input type="submit"/>
</form>
<hr/>
<button id="btn">전송</button>
- javascript 

	
$(document).ready(function() {
    $("form").submit(function(event) {
  	var id = $('#id').val();
    var pw = $('#pw').val();
    
   	if (id != "" && pw != "") {
    	alert("id :: " + id + ", pw :: " + pw);
    }
    
    if (id == "") {
    	alert("id를 입력해주세요.");
    	event.preventDefault();
      return;
    }
    
    if (pw == "") {
    	alert("pw를 입력해주세요.");
    	event.preventDefault();
      return;
    }   
  });
  
  $('#btn').click(function () {
  	$("form").submit();
  });
});


출처: https://dreamjy.tistory.com/27 [컬쳐N라이프]






</body>
</html>