<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="ua.ouija.simplecrud.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Books list:</h1>
	<div>
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Author</th>
				<th>Year</th>
				<th>Pages</th>
			</tr>
			<c:forEach items="${books}" var="book">
				<tr>
					<td>${book.getId()}</td>
					<td>${book.getName()}</td>
					<td><a
						href="BookServlet.do?action=by_author&author=<c:out value="${book.getAuthor()}"/>">${book.getAuthor()}</a></td>
					<td>${book.getYear()}</td>
					<td>${book.getPages()}</td>
					<td><a
						href="BookServlet.do?action=edit&id=<c:out value="${book.getId()}"/>">
							Edit</a></td>
					<td><a
						href="BookServlet.do?action=delete&id=<c:out value="${book.getId()}"/>">
							Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<p>
	<div>
		<a href="BookServlet.do?action=add">Add new book</a>
	</div>
</body>
</html>