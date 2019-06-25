<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>loginprocess</title>
</head>
<body>
	loginPoress.jsp<br>
	<%request.setCharacterEncoding("UTF-8"); %>
	 <h2>request객체의 getParameter 와 getParameterValue의 차이</h2>
	 <p>request.getParameterValues("userId")는 스트링 배열로 나온다. 그러므로 그에 대한 값을 꺼내줘야된다. for문을 이용</p>
	 
	 request.getParameter("userId") : <%= request.getParameter("userId") %><br>
	 request.getParameterValues("userId") : <% String[] userIds = request.getParameterValues("userId"); 
	 											for(String userId : userIds){%>
	 											<%=userId%>
	 											<%} %><br>
	 request.getParameter("password") : <%= request.getParameter("password") %><br>
	 
	 
	 
	 
	 
	 
	 <h2>request객체의 getParameterMap</h2>
	  request.getParameterMap() : <%= request.getParameterMap() %><br>
	 <% Map<String, String[]> parameterMap = request.getParameterMap(); 
	 	//우리가 알고있는 키는 parameter : userId, password 
	 	String[] userIdsFromMap = parameterMap.get("userId");
	 	String[] passwords = parameterMap.get("password");
	 	for(String userId : userIdsFromMap){ %>
	 	userIdsFromMap : <%= userId%> <br>
	 	<%} 
	 	for(String password : passwords){ %>
	 	passwords : <%= password%>
	 	<%} %>
	 	
	 	
	 	
	 	
	 	<h2>request객체의 getParameterNames()</h2>
	 	<%Enumeration<String> parameterNames = request.getParameterNames(); 
	 		while(parameterNames.hasMoreElements()){	%>
	 			parameterNames : <%= parameterNames.nextElement()%><br>
	 		<%} %>
	 
	
	 
	
		


</body>
</html>