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
<title>관리자</title>
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
},300000); 
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

<table border="1" class="mytable" id="mytable">
</table><br>
<ul class="list_list" id="list_list">
</ul>

</div>
<style>

td{
	padding: 10px;
}
.list_list {
	list-style : none;
	text-align:center;
}

.tb {
	padding-left: 20%;
}

.mytable {
	padding: 5%;
	padding: 15px;
	margin: auto;
	
}

</style>
<script>



$(function(){
	
 $('#1').on('click', function(e) {
	 $('#list_list').empty();
	 $( '#mytable').empty();
	
	 
 	$.ajax({
        	url:'../ManagerListControl',
        	type:'POST',
        	success : function(data){
				console.log(data);
				
				var a = data;
				var str = a.split('/');
				var list=["번호","등급","ID","이름","번호"];
				
				tr0();
				for(i=0;i<list.length;i++){
					td(list[i]);
				}
				
				n=0;
				for(i=0;i<str.length-1;i++){
					tr();
					n++;
					td0(n);
					var arr = str[i].split(',');
					for(j=0;j<arr.length;j++){
						td(arr[j]+" ");
					}
					<!--li(str[i]);-->
				}
				$list = $('ul');
				$list.append('<li >' +'<a href="managerjoin.jsp"<button id="adder" class="btn btn-primary" >관리자 추가</button></a> &#09; <a href="managerexpire.jsp"<button id="adder" class="btn btn-primary" >관리자 제거</button></a>'+ '</li>');
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

function tr0(){
	$list = $('#mytable');
    $list.append('<tr>' + '</tr>');
};

function td0(a){
	$list = $('#mytable');
    $list.append('<td>' + a+ '</td>');
};



</script>

<script>
$(function(){
	
 $('#2').on('click', function(e) {
	 $('#list_list').empty();
	 $('#list_list').empty();
	 $(function () {
	        $( '#mytable').empty();
	});
	 
 	$.ajax({
        	url:'../StoreListControl',
        	type:'POST',
        	success : function(data){
				console.log(data);
				
				var a = data;
				var str = a.split('/');
				var list=["번호","가맹점 이름","가입일","종료일","할인금액",""];
				
				tr1();
				for(i=0;i<list.length;i++){
					td(list[i]);
				}
				tr2();
				
				n=0;
				for(i=0;i<str.length-1;i++){
					tr();
					n++;
					td(n);
					var arr = str[i].split(',');
					for(j=0;j<arr.length;j++){
						td(arr[j]+" ");
					}
					<!--li(str[i]);-->
				}
				$list = $('ul');
				$list.append('<li >' +'<a href="/Park/store/storejoin.jsp"<button id="adder" class="btn btn-primary" >가맹점 추가</button></a> &#09; <a href="/Park/store/storeexpire.jsp"<button id="adder" class="btn btn-primary" >가맹점 제거</button></a>'+ '</li>');     
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
	 
	 $( '#mytable').empty();
	 
 	$.ajax({
        	url:'../PaymentListControl',
        	type:'POST',
        	success : function(data){
				console.log(data);
				
				var a = data;
				var str = a.split('/');
				var list=["번호","거래번호","차번","가격","상태","사유","거래일","할인","할인점"];
				
				tr1();
				for(i=0;i<list.length;i++){
					td(list[i]);
				}
				tr2();
				
				n=0;
				for(i=0;i<str.length;i++){
					tr();
					n++;
					td(n);
					var arr = str[i].split(',');
					for(j=0;j<arr.length;j++){
						td(arr[j]+" ");
					}
					<!--li(str[i]);-->
				}
        	},
        		error:function(data){
        			console.log(data);
        	}
        
        });
 });
}); 

function tr1(){
	$list = $('#mytable');
    $list.append('<tr>');
};

function tr2(){
	$list = $('#mytable');
    $list.append('</tr>');
};

function tr(){
	$list = $('#mytable');
    $list.append('<tr>' + '</tr>');
};

function td(a){
	$list = $('#mytable');
    $list.append('<td>' + a+ '</td>');
};

function li(a){
	$list = $('ul');
    $list.append('<li>' + a+ '</li>');
};

</script>

<script>
$(function(){
	
 $('#4').on('click', function(e) {
	 $('#list_list').empty();
	 $( '#mytable').empty();
	 
 	$.ajax({
        	url:'../MemberListControl',
        	type:'POST',
        	success : function(data){
				console.log(data);
				
				var a = data;
				var str = a.split('/');
				var list=["번호","차번","가입일","종료일"];
				
				
				tr();
				for(i=0;i<list.length;i++){
					td(list[i]);
				}

				n=0;
				for(i=0;i<str.length;i++){
					tr();
					n++;
					td(n);
					var arr = str[i].split(',');
					for(j=0;j<arr.length;j++){
						td(arr[j]+" ");
					}
					<!--li(str[i]);-->
				}
        	},
        		error:function(data){
        			console.log(data);
        	}
        
        });
 });
}); 


function tr(){
	$list = $('table');
    $list.append('<tr>' + '</tr>');
};

function td(a){
	$list = $('table');
    $list.append('<td>' + a+ '</td>');
};

function li(a){
	$list = $('ul');
    $list.append('<li>' + a+ '</li>');
};

</script>


</body>
</html>