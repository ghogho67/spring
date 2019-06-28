<%@page import="kr.or.ddit.page.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	
						<h2 class="sub-header">사용자(Tiles)</h2>
						
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
								
								
								<c:forEach items="${data.userList }" var="user" varStatus ="status">
									
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
							
							<c:forEach begin="1" end="${data.paginationSize }" step = "1" var = "i">
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