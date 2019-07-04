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
	
	
	//requsetData 클릭시 이벤트 핸들러
	$("#requestDataResponseBody").on("click",function(){
		
		$.ajax({
			url:"/ajax/requestDataResponseBody",
			method:"post",
			success:function(data){
				console.log(data);
				//data 형태는 data:{page : 5, pageSize : 10} 으로 들어온다.
				//data.pageVo : 는  {pageVo : {page:5, pageSize:10}} 이기때문에 전엔 data.pageVo.page 로 뽑은것이다.
				
				$("#pageResponseBody").text(data.page);
				$("#pageSizeResponseBody").text(data.pageSize);
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
	
	//전송할  json 객체를 준비
	/*
	우리가 자바스크립트로 할것
	public lcass UserVo(){
		private String userid;
		pricate String pass;
		private Stirng userId().....
	}
	
	UserVo user = new User Vo();
	user.setUserId("brown")
	객체를 선언하고 그 객체를 set 해줄려는걸 해볼려한다./
	
	
	*/
	
	var user = {userId:"brown", pass:"brown1234"}; //간단하게 객체를 만든다 자바 객체를 만들기 위한 리터럴(값)이다.
	//JSON.Sringify(): 자바스크립트 객체를 json문자열로 생성
	//JSON.parse("json문자열"): json문자열을 자바 스크립트 객체로 변경
	
	$("#userJsonString").text("userid=brown&pass=borwn1234");
	$("#userJsonString").text(JSON.stringify(user));
	
	//@ResponseBody 데이터 전송
	
	$("#userJsonStringBtn").on("click",function(){
		
		$.ajax({
			url : "/ajax/requestBody",
			method : "post",
			contentType : "application/json", //ajax를 통해서 보내는 데이터 형식이 json임을 알려준다.
// 			dataType :"xml",
			dataType :"json",				  //server축에 받고자 하는 데이터타입(Accept헤더)
			data : JSON.stringify(user),
			success : function(data){
				console.log(data);
				//xml
// 				$("#userJsonResult .userId").text(data.getElementsByTagName("userId")[0].textContent);
// 				$("#userJsonResult .pass").text(data.getElementsByTagName("pass")[0].textContent);
				
				//json
				$("#userJsonResult .userId").text(data.userId);
				$("#userJsonResult .pass").text(data.pass);
				
			}
		});
	});
	
	
	
});
</script>

<h2>ajax json 데이터 요청</h2>
<a id="requestData">데이터가져오기</a><br>
page: <span id="page"></span><br>
pageSize:<span id="pageSize"></span><br>

<h2>ajax json 데이터 요청(ResponseBody)</h2>
<a id="requestDataResponseBody">데이터가져오기</a><br>
page: <span id="pageResponseBody"></span><br>
pageSize:<span id="pageSizeResponseBody"></span><br>

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

<h2>ajax json 데이터 보내기</h2>
<a id="userHtml">데이터 가져오기</a>
요청보내는 데이터 <div id="userJsonString"></div>
받는 데이터 : <div id="userInfo"></div>

<h2>ajax json 데이터 보내기</h2>
<a id="userJsonStringBtn">데이터 가져오기</a>
요청보내는 데이터(기존) <div id="userFormString"></div>
요청보내는 데이터 : <div id="userJsonString"></div>
받는데이터:
<div id="userJsonResult">
	userId:<span class="userId"></span><br>
	pass: <span class ="pass"></span>
</div>
