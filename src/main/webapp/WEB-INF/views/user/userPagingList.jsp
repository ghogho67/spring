<%@page import="kr.or.ddit.page.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">




<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>사용자 리스트</title>
<!-- css,js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp" %>

<style type="text/css">
	.userTr:hover{
		cursor: grab;
	}
	.userTr:press{
		cursor: grabbing;
	}
	
</style>
<script type="text/javascript">
$(document).ready(function(){
	$(".userTr").on("click",function(){
		console.log("userTr");
		//userId를 획득하는 방법
		//$(this).find(".userId").text()
		//$(this)클릭했을때.data("userId")
		
		//사용자 아이디를 #userId 값으로 설정해주고
		var userId = $(this).find(".userid").text();
		$("#userId").val(userId);
		
		//#frm 을 이용하여 submit();
		$("#frm").submit();
		
	});
});

</script>
</head>

<body>
	

	<!-- header -->
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	
	
	<div class="container-fluid">
		<div class="row">
	<!-- left -->
	<%@include file="/WEB-INF/views/common/left.jsp" %>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">사용자</h2>
						
						<!-- 사용자 상세조회 : userId가 필요 이 form 태그는 화면상 안보일꺼임 -->
						<form id ="frm" action="${cp }/user/user" method="get">
							<input type="hidden" id="userId" name="userId"/>
						
						</form>
						
						
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>사용자 아이디</th>
									<th>사용자 이름</th>
									<th>사용자 별명</th>
									<th>등록일시</th>
								</tr>
								
								
								<c:forEach items="${userList }" var="user" varStatus ="status">
									
								<tr class="userTr" data-userid="${user.userId }">
									<!-- ${status.index}/${status.count}/ 카운터 주기-->
									<td class="userid">${user.userId }</td>
									<td>${user.name }</td>
									<td>${user.alias}</td>
									<td></td>
								</tr>
								</c:forEach>
							</table>
						</div>

						<a  href="${cp}/user/form" class="btn btn-default pull-right">사용자 등록</a>
						<a  href="${cp}/user/userListExcel" class="btn btn-default pull-right">엑셀다운로드</a>

						<div class="text-center">
							<ul class="pagination">
							<%-- 
							<li <%if(pagevo.getPage() == 1){ 
								%> class = "disabled"> 
									 <span>«</span> </li>
								<%}else if(pagevo.getPage() != 1){
									int a = pagevo.getPage() - 1;	 
										%> 
								<li><a href = "${pageContext.request.contextPath}/userPagingList?page=<%=a%>
																			">«</a></li>
								<%}%>
								--%>
								
								<c:choose>
									<c:when test="${pageVo.page == 1}">
										<li class = "disabled">
										 <span>«</span> </li>
									</c:when>
									<c:otherwise>
									<li><a href = "${cp}/user/pagingList?page=${pageVo.page -1 }& pagesize=${pageVo.pageSize }
																			">«</a></li>
									</c:otherwise>
								</c:choose>
							
							
					<%-- 		<%
							 int paginationSize = (Integer)request.getAttribute("paginationSize");
							for(int i = 1; i <= paginationSize; i++){
													
								if(pagevo.getPage() == i){%>
								<li	class ="active">
									<span><%=i%></span>
								</li>
								
								<%}else{ %>
								
								<li>
								<a href= "${pageContext.request.contextPath}/userPagingList?page=<%=i%>
																			&pageSize=<%=pagevo.getPageSize()%>"> <%=i%> </a></li>
								<%} %>
							<%}	%>
							--%>
							
							<c:forEach begin="1" end="${paginationSize }" step = "1" var = "i">
								<c:choose >
									<c:when test="${pageVo.page == i}">
									<li	class ="active">
										<span>${pageVo.page }</span>
									</li>
									</c:when>
							<%-- --%>	<c:otherwise>
										<li>
										<a href= "${cp}/user/pagingList?page=${i }&pageSize=${pageVo.pageSize}"> ${i} </a></li>
										</c:otherwise>
								</c:choose>
							</c:forEach>											
							
							
							
							
							
<%-- 								<li <%if(pagevo.getPage() == paginationSize){  --%>
<%-- 								%> class = "disabled"> --%>
<!-- 									 <span>»</span> </li> -->
<%-- 								<%}else if(pagevo.getPage() != paginationSize){ --%>
<!-- // 									int a = pagevo.getPage() + 1;	 -->
<%-- 										%> --%>
<%-- 								<li><a href = "${pageContext.request.contextPath}/userPagingList?page=<%=a%> --%>
<%-- 																			">»</a></li> --%>
<%-- 								<%}%> --%>


								<c:choose>
									<c:when test="${pageVo.page == paginationSize}">
										<li class = "disabled">
											 <span>»</span> </li>
									</c:when>
									<c:otherwise>
									<li><a href = "${cp}/user/pagingList?page=${pageVo.page +1 }& pageSize=${pageVo.pageSize }
																				">»</a></li>
									</c:otherwise>
								</c:choose>
							</ul>
							
						</div>
					</div>
				</div>

				<div class="row"></div>
				<!-- /.blog-main -->
			</div>
		</div>
	</div>
</body>
</html>
