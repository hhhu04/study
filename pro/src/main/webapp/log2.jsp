<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="js.Test" %>
<!DOCTYPE html>
<html>
<head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     <form id="newItem" name="f" >
         <input type="email" id="email" name="email">
         <input type="password" id="pass" name="pass">
         <input type="button" id="add" value="추가">
     </form>
        
<form id=out name=out>
	<input type="email" id="email2" name="email2">
	<input type="button" id="btn" value="검색">
</form>

<p class = "p" ></p>

<script>
$(function(){

    var $newForm;

    $newForm = $('#newItem');
    
    
    $('#add').on('click', function(e) {
        e.preventDefault();
       // var email = document.f.email.value;
      //  var pass = document.f.pass.value;
        var str = $("#newItem").serialize();
      //  console.log(email);
       // console.log(pass);
        
        $.ajax({
        	url:'./Test',
        	type:'POST',
        	data : str,
        	dataType:'json',
        	success : function(data){
				console.log(response.val);
				
        	},
        		error:function(data){
        	}
        	
        });
        
       
    });

});
</script>
<script>
$(function(){

    var $out;

    $newForm = $('#out');
    
    $('#btn').on('click', function(e) {
        e.preventDefault();
        var str2 = $("#out").serialize();
        
        $.ajax({
        	url:'./Test',
        	type:'POST',
        	data : str2,
        	dataType:'json',
        	success : function(data){
        		console.log(response.val);
        	},
        		error:function(data){
        	}
        	
        	
        });
        
       
    });

});
	
<!-- 	beforeSend: function (xhr) {
    xhr.setRequestHeader("Content-type","application/json");
    xhr.setRequestHeader("Authorization", str);
}, -->



</script>





</body>
</html>