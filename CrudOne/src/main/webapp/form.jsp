<%@ page language="java" contentType="text/html; ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="ua.ouija.simplecrud.*, java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />


<h1><c:out value="${message}" /></h1>
<p>
<h1><c:out value="${errmessage}" /></h1>
<p>
<c:forEach 
var="errField" items="${errors}">
 <c:out value="${errField}" /> <br/>
</c:forEach>
<p>


<form action="BookServlet.do" method="post">
<input type="hidden" name="id" value="<c:out value="${book.getId()}"/>">
<table>
<tr><td>Name</td>
<td><input type="text" name="name" value="<c:out value="${book.getName()}"/>" placeholder="Book Name" required></td></tr>
<tr><td>Author</td>
<td><input type="text" name="author" value="<c:out value="${book.getAuthor()}" />" placeholder="Author" required ></td></tr>
<tr><td>Year</td>
<td><input type="number" name="year" value="<c:out value="${book.getYear()}" />" min=1501 max="${year}" required></td></tr>
<tr><td>Price</td>
<td><input type="number" name="pages" value="<c:out value="${book.getPages()}" />" min=1 max=2000 required></td></tr>
<tr><td>
<button type="submit">Submit</button></td></tr>
</table>
</form>


<p>
<c:forEach items="${requestScope}" var="p"> 
       <li>${p.key} -> ${p.value}</li> 
     </c:forEach>

</body>
</html>
