<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(document).ready(function(){
	//select boax option 설정
// 	$("select[name=lang]").val("${param.lang}") //localeController.java 에 lang 이라는 인자값이없을떄
	$("#lang").val("${lang}")
	
	/* $("select[name=lang]").on("change",function(){
		$("select[name=lang]").parent().submit();
	}); */
	
	$("#lang").on("change",function(){
		$("#frm").submit();
	});
	
	
});




</script>


<form id="frm" action="/locale/view" method="post">	
	<select id="lang" name="lang">
		<option value="ko">한국어</option>
		<option value="en">English</option>
		<option value="ja">日本語</option>
	
	</select>
</form>


GREETING :<spring:message code="GREETING"/> <br>
VISITOR : <spring:message code="VISITOR">
			<spring:argument value="Borwn"/>
		  </spring:message>