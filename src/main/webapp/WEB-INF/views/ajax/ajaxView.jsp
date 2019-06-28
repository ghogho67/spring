<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
$(document).ready(function(){
	console.log("ready");
	
	//requsetData 클릭시 이벤트 핸들러
	$("#requestData").on("click",function(){
		$.ajax({
			url:"/ajax/requestData",
			method:"post",
			success:function(data){
				console.log(data);
				$("#page").text(data.pageVo.page);
				$("#pageSize").text(data.pageVo.pageSize);
			}
		});
	});
	
	
	//user 클릭시 이벤트 핸들러
	$("#user").on("click",function(){
		$.ajax({
			url: "/ajax/user",
			method: "post",
			data: "userId=" + $("#userId").val(),
			success:function(data){
				console.log(data);
				/*
				name: <input type="text" id="name" readonly/> <br>
				alias: <input type="text" id="alias" readonly/> <br>
				birth: <input type="text" id="birth" readonly/> <br>
				*/
				var html = "";
				html += "name: <input type=\"text\" id=\"name\" readonly value = \""+data.userVo.name+"\"/><br>";
				html += "alias: <input type=\"text\" id=\"alias\" readonly value = \""+data.userVo.alias+"\"/><br>";
				html += "birth: <input type=\"text\" id=\"birth\" readonly value = \""+data.userVo.birth+"\"/><br>";
				
// 				$("#name").val(data.userVo.name);
// 				$("#alias").val(data.userVo.alias);
// 				$("#birth").val(data.userVo.birth);

				$("#userJsonInfo").html(html);
				
			}
		});
	});
	
	//userHtml클래스 이벤트 핸들러 jsp 를 통한 ajax 처리
	$("#userhtml").on("click", function(){
		$.ajax({
			url:"/ajax/userHtml",
			method: "post",
			data : $("#frm").serialize(),
			success : function(data){
				console.log(data);
				$("#userInfo").html(data);
			}
			
		});
	});
	
	
});
</script>

<h2>ajax json 데이터 요청</h2>
<a id="requestData">데이터가져오기</a><br>
page: <span id="page"></span><br>
pageSize:<span id="pageSize"></span><br>



<h2>ajax json 데이터 요청(user)</h2>
<a id="user">데이터가져오기</a><br>
userId: <input type="text" id="userId" value="brown"/> <br>
<div id="userJsonInfo">
</div>



<h2>ajax html 데이터 요청(user)</h2>
<a id="userhtml">데이터가져오기</a><br>
<form id="frm">
userId: <input type="text" id="userIdHtml" name="userId" value="brown"/> <br>
</form>
<div id = "userInfo">
</div>

