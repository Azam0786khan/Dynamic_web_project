<%@page import="in.com.rays.bean.MarksheetBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<form action="MarksheetCtl.do" method="post">
		<div align="center">
			<%
			String msg = (String) request.getAttribute("msg");
			MarksheetBean bean = (MarksheetBean) request.getAttribute("bean");
			%>
			<%
			if (bean != null && bean.getId() > 0) {
			%>
			<h1>Update Marksheet</h1>
			<%
			} else {
			%>
			<h1>Add Marksheet</h1>
			<%
			}
			%>
			<%
			if (msg != null) {
			%>
			<h3>
				<font color="green"><%=msg%></font>
			</h3>
			<%
			}
			%>
			<table>
				<input type="hidden" name="id"
					value="<%=(bean != null && bean.getId() > 0) ? bean.getId() : ""%>">
				<tr>
					<th>Roll No:</th>
					<td><input type="text" name="rollNo"
					  value="<%=bean != null && bean.getRollNo() > 0 ? bean.getRollNo() : ""%>"></td>
				</tr>
				<tr>
					<th>Name:</th>
					<td><input type="text" name="name"
					value="<%=bean != null && bean.getname() != null ? bean.getname() : ""%>"></td>
				</tr>
				<tr>
					<th>Physics:</th>
					<td><input type="text" name="physics"
					value="<%=bean != null && bean.getPhysics() > 0 ? bean.getPhysics() : ""%>"></td>
				</tr>
				<tr>
					<th>Chemestry:</th>
					<td><input type="text" name="chemistry"
					value="<%=bean != null && bean.getChemistry() > 0 ? bean.getChemistry() : ""%>"></td>
				</tr>
				<tr>
					<th>Maths:</th>
					<td><input type="text" name="maths"
					value="<%=bean != null && bean.getMaths() > 0 ? bean.getMaths() : ""%>"></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=(bean != null && bean.getId() > 0) ? "update" : "save"%>"></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>