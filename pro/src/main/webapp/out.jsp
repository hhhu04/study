<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="js.Test" %>
    <%@ page import="js.Out" %>
  <%@ page import="java.io.PrintWriter"%> 
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form id=out name=out method="GET">
	<input type="email" id="email2" name="email2">
	<input type="button" id="btn" value="검색">
</form>

<button><a href="./index.jsp">홈</a></button>

<script>
$(function(){

    var $out;

    $newForm = $('#out');
    
    $('#btn').on('click', function(e) {
        e.preventDefault();
       // var str2 = $("#email2").val();
        var str2 = $("#out").serialize();
        $.ajax({
        	url:'./Test',
        	type:'GET',
        	data : str2,
        	dataType:'text',
        	
        	
        	success : function(data){
        		console.log(data);
        	},
        		error:function(data){
        	}
        	
        });
        
        
    });

});
	




</script>


</body>




</html>