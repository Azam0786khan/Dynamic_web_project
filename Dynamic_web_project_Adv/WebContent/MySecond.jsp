<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		for (int i = 1; i <= 5; i++) {
	%>
	<h3> <%=i%>. My Second JSP page</h3>
	<%
		}
	%>

</body>
</html>