<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	main.jsp <br>
	mainUserId : ${mainUserId } <!-- 옆애 애는 pageContext.getAttribute("mainUserId") 하고 
									 request.getAtrribute("mainuserId")
									 session.getAtrribute("mainUserId")
									 application.getAtrribute("mainUserId") 순으로 검색한다.-->
</body>
</html>