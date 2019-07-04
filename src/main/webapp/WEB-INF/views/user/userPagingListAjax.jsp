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
	$("#userListTbody").on("click",".userTr",function(){
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
	
	
	//ajax 처리 첫번째 페이지의 사용자 정보를 요청
// 	userPagingListAjax(1,10);
	userPagingListAjaxHtml(1,10);
});

//데이터 응답을 html로 받는 경우
function userPagingListAjaxHtml(page, pageSize){
	$.ajax({
		url:"/user/pagingListAjaxHtml",
// 		method "post",
		data : "page=" + page + "&pageSize=" + pageSize,
		success : function(d){
			//html
			var html = d.split("SPERATORSPERATOR");
			$("#userListTbody").html(html[0]);
			$(".pagination").html(html[1]);			
		}
	});
	
}


//더이터 응답을 json으로 받는 경우
function userPagingListAjax(page, pageSize){
	$.ajax({
		url:"/user/pagingListAjax",
// 		method "post",
		data : "page=" + page + "&pageSize=" + pageSize,
		success : function(d){
			//data.data.userList 로 들어간다
			console.log(d);
			var html = "";
			d.data.userList.forEach(function(user){
				//html생성
				html += "<tr class='userTr' data-userid='"+ user.userId+"'>";
				html += "<td class='userId'> "+ user.userId+"</td>";
				html += "<td>"+ user.name+"</td>";
				html += "<td>"+ user.alias+"</td>";
				html += "<td></td>";
				html += "</tr>";
				
			});
			
			//페이지네이션 로직 작성!
			var pHtml = "";
			var pageVo = d.pageVo;
			if(pageVo.page ==1){
				pHtml += "<li class = 'disabled'><span>«</span></li>";
			
			
			}else{
				pHtml += "<a href ='javascript:userPagingListAjax("+(pageVo.page -1) +","+pageVo.pageSize+");'>«</a></li>";
			}
			
			for(var i = 1; i<= d.data.paginationSize; i++){
				if(pageVo.page == i){
					
					pHtml += "<li class = 'active'><span>"+i+"</sapn></li>";
				
				}
				else{
					pHtml += "<li><a href='javascipt:userPagingListAjax("+i+","+pageVo.pageSize+");'>"+i+"</a></li>";
				}
			}
				
				if(pageVo.page == d.data.paginationSize){
					pHtml += "<li class = 'disabled'><span>»</span></li>";
				}else{
				pHtml += "<li><a href='javascript:userPagingListAjax("+(pageVo.page+1)+ "," +pageVo.pageSize+");'>»</a></li>"
				}		
				 
			
			
			console.log("html:",html);
			console.log("pHtml:",pHtml);
			$("#userListTbody").html(html);
			$(".pagination").html(pHtml);
		}
	});
}

</script>
	
						<h2 class="sub-header">사용자(Tiles)</h2>
						
						<!-- 사용자 상세조회 : userId가 필요 이 form 태그는 화면상 안보일꺼임 -->
						<form id ="frm" action="${cp }/user/user" method="get">
							<input type="hidden" id="userId" name="userId"/>
						
						</form>
						
						
						<div class="table-responsive">
							<table class="table table-striped">
							 <thead>	
								<tr>
									<th>사용자 아이디</th>
									<th>사용자 이름</th>
									<th>사용자 별명</th>
									<th>등록일시</th>
								</tr>
							 </thead>
							 <tbody id="userListTbody">
							 </tbody>								
							</table>
						</div>

						<a  href="${cp}/user/form" class="btn btn-default pull-right">사용자 등록</a>
						<a  href="${cp}/user/userListExcel" class="btn btn-default pull-right">엑셀다운로드</a>

						<div class="text-center">
							<ul class="pagination">
							
							</ul>
							
						</div>

				<div class="row"></div>
				<!-- /.blog-main -->