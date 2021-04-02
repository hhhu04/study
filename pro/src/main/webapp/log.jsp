<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="js.Test" %>
   <%@ page import="js.Out" %>
<!DOCTYPE html>
<html>
<head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     <form id="newItem" name="f" method="POST">
         <input type="email" id="email" name="email">
         <input type="password" id="pass" name="pass">
         <input type="button" id="add" value="추가">
     </form>
 
        
        

<p class = "p" ></p>

<script>
$(function(){

    var $newForm;

    $newForm = $('#newItem');
    
    
    $('#add').on('click', function(e) {
        e.preventDefault();
        var email = document.f.email.value;
      //  var pass = document.f.pass.value;
        var str = $("#newItem").serialize();
        console.log(email);
       // console.log(pass);
        
        $.ajax({
        	url:'./Test',
        	type:'POST',
        //	data : JSON.stringify(str),
        	data : str,
        	dataType : 'text',
        //	contentType: "application/json; charset=utf-8;",
        	success : function(data){
				console.log(data);
				
        	},
        		error:function(data){
        			console.log(data);
        	}
        	
        });
        
      $(location).attr("href", "/pro/index.jsp");
    });

});
</script>






</body>
</html>