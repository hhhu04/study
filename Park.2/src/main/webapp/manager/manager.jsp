<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="managerlist.ManagerListControl" %>   
<%@ page import = "setting.MemberControl" %>
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

<%
Cookie[] cookies = request.getCookies();
MemberControl mem = new MemberControl();
String mail;
int num=0;
String master = "0";
for(Cookie cookie : cookies)
{

	if(cookie.getName().equals("ID")) {
		if(cookie.getValue().equals("123")){
			master = cookie.getValue();
			cookie.setMaxAge(999);
			response.addCookie(cookie);
		}
		num++;
	}
}

if(master.equals("123")) {mem.cc();}
if(num == 0){
%>
<script>
alert("권한없음");
$(location).attr('href','/Park/main.jsp');
</script>
<% 	
}
%>


<script>
setTimeout(function(){
location.reload();
},60000); 
</script>


<div class="box1">
<br><br><br>
<h4>관리자 매뉴</h4>
<button id="1" class="btn btn-primary" >직원 목록</button>
<button id="2" class="btn btn-primary" >가맹점 목록</button>
<button id="3" class="btn btn-primary" >정산 목록</button>
<button id="4" class="btn btn-primary" >정기회원 목록</button>
<button class="btn btn-primary" onclick="location.href='logout.jsp'">로그아웃</button>
<br><br>
<ul class="list_list" id="list_list">
</ul>

<table>
<tr>

</tr>
</table>


</div>
<style>
.list_list {
	list-style : none;
	text-align:left;
}

</style>
<script>
$(function(){
	
 $('#1').on('click', function(e) {
	 $('#list_list').empty();
	 
	
	 
 	$.ajax({
        	url:'../ManagerListControl',
        	type:'POST',
        	success : function(data){
				console.log(data);
				
				var a = data;
				var str = a.split('/');
				
				for(i=0;i<str.length;i++){
					li(str[i]);
				}
				$list = $('ul');
				$list.append('<li>' +'<a href="managerjoin.jsp"<button id="adder" class="btn btn-primary" >관리자 추가</button></a> &#09; <a href="managerexpire.jsp"<button id="adder" class="btn btn-primary" >관리자 제거</button></a>'+ '</li>');
        	},
        		error:function(data){
        			console.log(data);
        	}
        
        });
 });
}); 


function li(a){
	$list = $('ul');
    $list.append('<li>' + a+ '</li>');
};

</script>

<script>
$(function(){
	
 $('#2').on('click', function(e) {
	 $('#list_list').empty();
	 $('#list_list').empty();
 	$.ajax({
        	url:'../StoreListControl',
        	type:'POST',
        	success : function(data){
				console.log(data);
				
				var a = data;
				var str = a.split('/');
				
				for(i=0;i<str.length;i++){
					li(str[i]);
				}
				$list = $('ul');
				$list.append('<li>' +'<a href="storejoin.jsp"<button id="adder" class="btn btn-primary" >가맹점 추가</button></a> &#09; <a href="storeexpire.jsp"<button id="adder" class="btn btn-primary" >가맹점 제거</button></a>'+ '</li>');     
        	},
        		error:function(data){
        			console.log(data);
        	}
        
        });
 });
}); 

function li(a){
	$list = $('ul');
    $list.append('<li>' + a+ '</li>');
};

</script>

<script>
$(function(){
	
 $('#3').on('click', function(e) {
	 $('#list_list').empty();
	 
 	$.ajax({
        	url:'../PaymentListControl',
        	type:'POST',
        	success : function(data){
				console.log(data);
				
				var a = data;
				var str = a.split('/');
				
				for(i=0;i<str.length;i++){
					li(str[i]);
				}
        	},
        		error:function(data){
        			console.log(data);
        	}
        
        });
 });
}); 

function li(a){
	$list = $('ul');
    $list.append('<li>' + a+ '</li>');
};

</script>

<script>
$(function(){
	
 $('#4').on('click', function(e) {
	 $('#list_list').empty();
	 
 	$.ajax({
        	url:'../MemberListControl',
        	type:'POST',
        	success : function(data){
				console.log(data);
				
				var a = data;
				var str = a.split('/');
				
				for(i=0;i<str.length;i++){
					li(str[i]);
				}
        	},
        		error:function(data){
        			console.log(data);
        	}
        
        });
 });
}); 

function li(a){
	$list = $('ul');
    $list.append('<li>' + a+ '</li>');
};

</script>


</body>
</html>